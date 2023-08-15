package com.example.managepersonjob.service;

import com.example.managepersonjob.objectRequest.JobRequest;
import com.example.managepersonjob.objectRequest.StatusRequest;
import com.example.managepersonjob.objectResponse.ResponseAPI;
import org.springframework.stereotype.Service;

@Service
public interface StatusService {
    public ResponseAPI getPage(int page, int limit);
    public ResponseAPI getStatus();

    public ResponseAPI add(StatusRequest statusRequest);
    public ResponseAPI delete(Long id);
    public ResponseAPI update(Long id, StatusRequest statusRequest);
    public ResponseAPI search(int page, int limit,String textSearch);
    public ResponseAPI sort(int page, int limit, String sortType);
    public ResponseAPI sortSearch(int page, int limit, String sortType, String textSearch);
}
