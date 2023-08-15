package com.example.managepersonjob.service;

import com.example.managepersonjob.entity.DetailJobEntity;
import com.example.managepersonjob.entity.JobEntity;
import com.example.managepersonjob.entity.StatusEntity;
import com.example.managepersonjob.mapping.DetailJobMapping;
import com.example.managepersonjob.mapping.JobMapping;
import com.example.managepersonjob.objectRequest.DetailJobRequest;
import com.example.managepersonjob.objectResponse.DetailJobResponse;
import com.example.managepersonjob.objectResponse.JobResponse;
import com.example.managepersonjob.objectResponse.ResponseAPI;
import com.example.managepersonjob.repository.DetailJobRepository;
import com.example.managepersonjob.repository.JobRepository;
import com.example.managepersonjob.repository.StatusRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class DetailJobServiceImpl implements DetailJobService {
    @Autowired
    private DetailJobRepository detailJobRepository;
    @Autowired
    private JobRepository jobRepository;
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public ResponseAPI getPage(int page, int limit, Long idJob) {
        Pageable pageable = PageRequest.of(page, limit);
        try {
//            Page<DetailJobEntity> detailJobEntities = detailJobRepository.getListDetail(idJob,PageRequest.of(page, limit));
            Page<DetailJobEntity> detailJobEntities = detailJobRepository.getPage(idJob, pageable);

            List<DetailJobResponse> detailJobResponses = detailJobEntities.stream()
                    .map(DetailJobMapping::DetailEntityToDetailResponse).collect(Collectors.toList());
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(detailJobResponses);
            List<Integer> total = new ArrayList<Integer>();
            total.add(detailJobRepository.getListDetail(idJob).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI add(DetailJobRequest detailJobRequest) {
        DetailJobEntity detailJobEntity = new DetailJobEntity();
        try {
            detailJobEntity = DetailJobMapping.DetailRequestToDetailEntity(detailJobRequest);
            JobEntity jobEntity = jobRepository.getById(detailJobRequest.getIdJob());
            detailJobEntity.setJob(jobEntity);
            if (detailJobRequest.getIdStatus() == null) {
                StatusEntity statusEntity = statusRepository.getById(2L);
                detailJobEntity.setStatus(statusEntity);
            } else {
                StatusEntity statusEntity = statusRepository.getById(detailJobRequest.getIdStatus());
                detailJobEntity.setStatus(statusEntity);
            }
            detailJobRepository.save(detailJobEntity);
            // Tính tiến độ
            List<DetailJobEntity> detailJobEntities = detailJobRepository.getListDetail(detailJobRequest.getIdJob());
            int done = 0;
            for (DetailJobEntity detailJob : detailJobEntities) {
                if (detailJob.getStatus().getId()==1) {
                    done++;
                }
            }
            String process = done + "/" + detailJobEntities.size();
            jobEntity.setProcess(process);
            System.out.println(process);
            jobEntity.setId(detailJobRequest.getIdJob());
            jobRepository.save(jobEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI delete(Long id) {
        try {
            DetailJobEntity detailJobEntity = detailJobRepository.getById(id);
            detailJobRepository.delete(detailJobEntity);
            // Tính tiến độ
            JobEntity jobEntity = jobRepository.getById(detailJobEntity.getJob().getId());
            List<DetailJobEntity> detailJobEntities = detailJobRepository.getListDetail(detailJobEntity.getJob().getId());
            int done = 0;
            for (DetailJobEntity detailJob : detailJobEntities) {
                if (detailJob.getStatus().getId()==1) {
                    done++;
                }
            }
            String process = done + "/" + detailJobEntities.size();
            jobEntity.setProcess(process);
            System.out.println(process);
            jobEntity.setId(detailJobEntity.getJob().getId());
            jobRepository.save(jobEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI update(Long id, DetailJobRequest detailJobRequest) {
        try {
            DetailJobEntity detailJobEntity = DetailJobMapping.DetailRequestToDetailEntity(detailJobRequest);
            detailJobEntity.setId(id);
            JobEntity jobEntity = jobRepository.getById(detailJobRequest.getIdJob());
            detailJobEntity.setJob(jobEntity);
            StatusEntity statusEntity = statusRepository.getById(detailJobRequest.getIdStatus());
            detailJobEntity.setStatus(statusEntity);
            detailJobRepository.save(detailJobEntity);
            // Tính tiến độ
            List<DetailJobEntity> detailJobEntities = detailJobRepository.getListDetail(detailJobRequest.getIdJob());
            int done = 0;
            for (DetailJobEntity detailJob : detailJobEntities) {
                if (detailJob.getStatus().getId()==1) {
                    done++;
                }
            }
            String process = done + "/" + detailJobEntities.size();
            jobEntity.setProcess(process);
            System.out.println(process);
            jobEntity.setId(detailJobRequest.getIdJob());
            jobRepository.save(jobEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI search(int page, int limit, String textSearch,Long idJob) {
        Pageable pageable = PageRequest.of(page, limit);
        try {
            Page<DetailJobEntity> detailJobEntities = detailJobRepository.searchDetailJob(textSearch, pageable,idJob);
            List<DetailJobResponse> detailJobResponse = detailJobEntities.
                    stream().map(DetailJobMapping::DetailEntityToDetailResponse).
                    collect(Collectors.toList());
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(detailJobResponse);
            List<Integer> total = new ArrayList<Integer>();
            total.add(detailJobRepository.totalSearchDetailJob(idJob,textSearch).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI sort(int page, int limit, String sortType,Long idJob) {
        Pageable pageable = PageRequest.of(page, limit);
        List<DetailJobResponse> detailJobResponse = new ArrayList<>();
        try {
            if (sortType.equals("asc")) {
                Page<DetailJobEntity> detailJobEntities = detailJobRepository.sortDetailJobAsc(idJob, sortType,pageable);
                detailJobResponse = detailJobEntities.stream().
                        map(DetailJobMapping::DetailEntityToDetailResponse).collect(Collectors.toList());
            } else {
                Page<DetailJobEntity> detailJobEntities = detailJobRepository.sortDetailJobDesc(idJob,pageable, sortType);
                detailJobResponse = detailJobEntities.stream().
                        map(DetailJobMapping::DetailEntityToDetailResponse).collect(Collectors.toList());
            }
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(detailJobResponse);
            List<Integer> total = new ArrayList<Integer>();
            total.add(detailJobRepository.getListDetail(idJob).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI sortSearch(int page, int limit, String sortType, String textSearch,Long idJob) {
        Pageable pageable = PageRequest.of(page, limit);
        List<DetailJobResponse> detailJobResponse = new ArrayList<>();
        try {
            if (sortType.equals("asc")) {
                Page<DetailJobEntity> detailJobEntities = detailJobRepository.sortASCSearchDetailJob(textSearch, pageable,idJob);
                detailJobResponse = detailJobEntities.stream().
                        map(DetailJobMapping::DetailEntityToDetailResponse).collect(Collectors.toList());
            } else {
                Page<DetailJobEntity> detailJobEntities = detailJobRepository.sortDESCSearchDetailJob(textSearch, pageable,idJob);
                detailJobResponse = detailJobEntities.stream().
                        map(DetailJobMapping::DetailEntityToDetailResponse).collect(Collectors.toList());
            }
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(detailJobResponse);
            List<Integer> total = new ArrayList<Integer>();
            total.add(detailJobRepository.totalSearchDetailJob(idJob,textSearch).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }

    }

    @Override        // xoa duoc
    public List<DetailJobResponse> searchTest(String textSearch, Long idJob) {
        List<DetailJobEntity> detailJobEntities= detailJobRepository.totalSearchDetailJob(idJob,textSearch);
        List<DetailJobResponse> detailJobResponse = detailJobEntities.stream().
                map(DetailJobMapping::DetailEntityToDetailResponse).collect(Collectors.toList());
        return detailJobResponse;
    }

}
