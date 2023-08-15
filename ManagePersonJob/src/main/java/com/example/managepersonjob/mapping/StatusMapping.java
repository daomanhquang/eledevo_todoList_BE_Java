package com.example.managepersonjob.mapping;

import com.example.managepersonjob.entity.DetailJobEntity;
import com.example.managepersonjob.entity.StatusEntity;
import com.example.managepersonjob.objectRequest.StatusRequest;
import com.example.managepersonjob.objectResponse.DetailJobResponse;
import com.example.managepersonjob.objectResponse.StatusResponse;

import java.util.List;

public class StatusMapping {
    public static StatusEntity StatusRequestToStatusEntity (StatusRequest statusRequest){
        StatusEntity statusEntity = new StatusEntity();
        statusEntity.setName(statusRequest.getName());
        return statusEntity;
    }
    public static StatusResponse StatusEntityToStatusResponse(StatusEntity statusEntity){
        StatusResponse statusResponse = new StatusResponse();
        statusResponse.setId(statusEntity.getId());
        statusResponse.setName(statusEntity.getName());
        return statusResponse;
    }
}
