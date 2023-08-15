package com.example.managepersonjob.service;

import com.example.managepersonjob.objectRequest.JobRequest;
import com.example.managepersonjob.objectResponse.ResponseAPI;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public interface JobService {
    public ResponseAPI getPage(int page, int limit);
    public ResponseAPI add(JobRequest jobRequest);
    public ResponseAPI delete(Long id);
    public ResponseAPI update(Long id, JobRequest jobRequest);
    public ResponseAPI search(int page, int limit,String textSearch);
    public ResponseAPI sort(int page, int limit, String sortType);
    public ResponseAPI sortSearch(int page, int limit, String sortType, String textSearch);




}
