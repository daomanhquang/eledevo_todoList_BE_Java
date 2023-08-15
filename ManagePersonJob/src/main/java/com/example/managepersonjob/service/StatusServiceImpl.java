package com.example.managepersonjob.service;

import com.example.managepersonjob.entity.StatusEntity;
import com.example.managepersonjob.mapping.StatusMapping;
import com.example.managepersonjob.objectRequest.StatusRequest;
import com.example.managepersonjob.objectResponse.ResponseAPI;
import com.example.managepersonjob.objectResponse.StatusResponse;
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
public class StatusServiceImpl implements StatusService {
    @Autowired
    private StatusRepository statusRepository;

    @Override
    public ResponseAPI getPage(int page, int limit) {

        Pageable pageable = PageRequest.of(page, limit);
        try {
            Page<StatusEntity> StatusEntityPage = statusRepository.findAll(pageable);
            List<StatusResponse> statusResponse = StatusEntityPage.stream()
                    .map(StatusMapping::StatusEntityToStatusResponse).collect(Collectors.toList());
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(statusResponse);
            List<Integer> total = new ArrayList<Integer>();
            total.add(statusRepository.findAll().size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);

        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI getStatus() {
        try {

            List<StatusEntity> statusEntities = statusRepository.findAll();
//            System.out.println(statusEntities);
            List<StatusResponse> statusResponse = statusEntities.stream()
                    .map(StatusMapping::StatusEntityToStatusResponse).collect(Collectors.toList());
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(statusResponse);

            return new ResponseAPI(200, "Success", content);
//            return null;
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
//            return null;

        }
    }

    @Override
    public ResponseAPI add(StatusRequest statusRequest) {

        StatusEntity statusEntity = new StatusEntity();
        try {
            statusEntity = StatusMapping.StatusRequestToStatusEntity(statusRequest);
            statusRepository.save(statusEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI delete(Long id) {
        try {
            StatusEntity statusEntity = statusRepository.getById(id);
            statusRepository.delete(statusEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }

    }

    @Override
    public ResponseAPI update(Long id, StatusRequest statusRequest) {
        try {
            StatusEntity statusEntity = StatusMapping.StatusRequestToStatusEntity(statusRequest);
            statusEntity.setId(id);
            statusRepository.save(statusEntity);
            return new ResponseAPI(200, "Success", null);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }
    @Override
    public ResponseAPI search(int page, int limit, String textSearch) {
        Pageable pageable = PageRequest.of(page, limit);
        try {
            Page<StatusEntity> statusEntities = statusRepository.searchStatus(textSearch, pageable);
            List<StatusResponse> statusResponse = statusEntities.
                    stream().map(StatusMapping::StatusEntityToStatusResponse).
                    collect(Collectors.toList());
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(statusResponse);
            List<Integer> total = new ArrayList<Integer>();
            total.add(statusRepository.totalSearchStatus(textSearch).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI sort(int page, int limit, String sortType) {
        Pageable pageable = PageRequest.of(page, limit);
        List<StatusResponse> statusResponse = new ArrayList<>();
        try {
            if (sortType.equals("asc")) {
                Page<StatusEntity> statusEntities = statusRepository.sortStatusAsc(pageable, sortType);
                statusResponse = statusEntities.stream().
                        map(StatusMapping::StatusEntityToStatusResponse).collect(Collectors.toList());
            } else {
                Page<StatusEntity> statusEntities = statusRepository.sortStatusDesc(pageable, sortType);
                statusResponse = statusEntities.stream().
                        map(StatusMapping::StatusEntityToStatusResponse).collect(Collectors.toList());
            }
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(statusResponse);
            List<Integer> total = new ArrayList<Integer>();
            total.add(statusRepository.findAll().size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        } catch (Exception e) {
            return new ResponseAPI(400, e.getMessage(), null);
        }
    }

    @Override
    public ResponseAPI sortSearch(int page, int limit, String sortType, String textSearch) {
        Pageable pageable = PageRequest.of(page, limit);
        List<StatusResponse> statusResponse=new ArrayList<>();
        try {
            if (sortType.equals("asc")){
                Page<StatusEntity>  statusEntities = statusRepository.sortASCSearchStatus(textSearch,pageable);
                statusResponse=statusEntities.stream().
                        map(StatusMapping::StatusEntityToStatusResponse).collect(Collectors.toList());
            }
            else{
                Page<StatusEntity>  statusEntities = statusRepository.sortDESCSearchStatus(textSearch,pageable);
                statusResponse=statusEntities.stream().
                        map(StatusMapping::StatusEntityToStatusResponse).collect(Collectors.toList());
            }
            List<List<?>> content = new ArrayList<List<?>>();
            content.add(statusResponse);
            List<Integer> total = new ArrayList<Integer>();
            total.add(statusRepository.totalSearchStatus(textSearch).size());
            content.add(total);
            return new ResponseAPI(200, "Success", content);
        }
        catch (Exception e){
            return new ResponseAPI(400, e.getMessage(), null);
        }

    }
}
