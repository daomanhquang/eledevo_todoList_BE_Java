package com.example.managepersonjob.service;

import com.example.managepersonjob.entity.DetailJobEntity;
import com.example.managepersonjob.entity.JobEntity;
import com.example.managepersonjob.mapping.JobMapping;
import com.example.managepersonjob.objectRequest.JobRequest;
import com.example.managepersonjob.objectResponse.JobResponse;
import com.example.managepersonjob.objectResponse.ResponseAPI;
import com.example.managepersonjob.repository.DetailJobRepository;
import com.example.managepersonjob.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class JobServiceImpl implements JobService {
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private DetailJobRepository detailJobRepository;

    @Override
    public ResponseAPI getPage(int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);
        try {
            Page<JobEntity> jobEntityPage = jobRepository.findAll(pageable);
            List<JobResponse> jobResponses = jobEntityPage.stream()
                    .map(JobMapping::JobEntityToJobResponse).collect(Collectors.toList());
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(jobResponses);
            List<Integer> total = new ArrayList<Integer>();
            total.add(jobRepository.findAll().size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI add(JobRequest jobRequest) {
        JobEntity jobEntity = new JobEntity();
        try {
            jobEntity = JobMapping.JobRequestToJobEntity(jobRequest);
            jobRepository.save(jobEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI delete(Long id) {
        try {
            JobEntity jobEntity = jobRepository.getById(id);
            jobRepository.delete(jobEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }

    }

    @Override
    public ResponseAPI update(Long id, JobRequest jobRequest) {
        try {
            JobEntity jobEntity = JobMapping.JobRequestToJobEntity(jobRequest);
            jobEntity.setId(id);
            List<DetailJobEntity> detailJobEntities = detailJobRepository.getListDetail(id);
            int done=0;
            for(DetailJobEntity detailJob : detailJobEntities){
                if(detailJob.getStatus().getName().equals("done")){
                    done++;
                }
            }
            String process=done+"/"+detailJobEntities.size();
            jobEntity.setProcess(process);
            jobRepository.save(jobEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI search(int page, int limit, String textSearch) {
        Pageable pageable = PageRequest.of(page, limit);
        try {
            Page<JobEntity> jobEntities = jobRepository.searchJob(textSearch, pageable);
            List<JobResponse> jobResponses = jobEntities.
                    stream().map(JobMapping::JobEntityToJobResponse).
                    collect(Collectors.toList());
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(jobResponses);
            List<Integer> total = new ArrayList<Integer>();
            total.add(jobRepository.totalSearchJob(textSearch).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }
    @Override
    public ResponseAPI sort(int page, int limit, String sortType) {
        Pageable pageable = PageRequest.of(page, limit);
        List<JobResponse> jobResponses = new ArrayList<>();
        try {
            if (sortType.equals("asc")) {
                Page<JobEntity> jobEntities = jobRepository.sortJobAsc(pageable, sortType);
//                Page<JobEntity> jobEntities= jobRepository.findAll(PageRequest.of(page, limit ,Sort.by("name").ascending()));
                jobResponses = jobEntities.stream().
                        map(JobMapping::JobEntityToJobResponse).collect(Collectors.toList());
            } else {
                Page<JobEntity> jobEntities = jobRepository.sortJobDesc(pageable, sortType);
//                Page<JobEntity> jobEntities= jobRepository.findAll(PageRequest.of(page, limit ,Sort.by("name").descending()));
                jobResponses = jobEntities.stream().
                        map(JobMapping::JobEntityToJobResponse).collect(Collectors.toList());
            }
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(jobResponses);
            List<Integer> total = new ArrayList<Integer>();
            total.add(jobRepository.findAll().size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI sortSearch(int page, int limit, String sortType, String textSearch) {
        Pageable pageable = PageRequest.of(page, limit);
        List<JobResponse> jobResponses=new ArrayList<>();
        try {
            if (sortType.equals("asc")){
                Page<JobEntity>  jobEntities = jobRepository.sortASCSearchJob(textSearch,pageable);
                jobResponses=jobEntities.stream().
                        map(JobMapping::JobEntityToJobResponse).collect(Collectors.toList());
            }
            else{
                Page<JobEntity>  jobEntities = jobRepository.sortDESCSearchJob(textSearch,pageable);
                 jobResponses=jobEntities.stream().
                        map(JobMapping::JobEntityToJobResponse).collect(Collectors.toList());
            }
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(jobResponses);
            List<Integer> total = new ArrayList<Integer>();
            total.add(jobRepository.totalSearchJob(textSearch).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        }
        catch (Exception e){
            return new ResponseAPI(400, e.getMessage(), null);
        }

    }
}

