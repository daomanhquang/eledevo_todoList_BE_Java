package com.example.managepersonjob.service;

import com.example.managepersonjob.entity.DetailJobEntity;
import com.example.managepersonjob.objectRequest.DetailJobRequest;
import com.example.managepersonjob.objectRequest.JobRequest;
import com.example.managepersonjob.objectResponse.DetailJobResponse;
import com.example.managepersonjob.objectResponse.ResponseAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface DetailJobService {
    public ResponseAPI getPage(int page, int limit,Long idJob);
    public ResponseAPI add(DetailJobRequest detailJobRequest);
    public ResponseAPI delete(Long id);
    public ResponseAPI update(Long id, DetailJobRequest detailJobRequest);
    public ResponseAPI search(int page, int limit,String textSearch,Long idJob);
    public ResponseAPI sort(int page, int limit, String sortType,Long idJob);
    public ResponseAPI sortSearch(int page, int limit, String sortType, String textSearch,Long idJob);

    public List<DetailJobResponse> searchTest(String textSearch, Long idJob);


}
