package com.example.managepersonjob.mapping;

import com.example.managepersonjob.entity.DetailJobEntity;
import com.example.managepersonjob.objectRequest.DetailJobRequest;
import com.example.managepersonjob.objectResponse.DetailJobResponse;
import com.example.managepersonjob.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;


public class DetailJobMapping {

    public static DetailJobResponse DetailEntityToDetailResponse (DetailJobEntity detailJobEntity){
        DetailJobResponse detailJobResponse = new DetailJobResponse();
//        detailJobResponse.setJobResponse(JobMapping.JobEntityToJobResponse(detailJobEntity.getJob()));
//        detailJobResponse.setStatusResponse(StatusMapping.StatusEntityToStatusResponse(detailJobEntity.getStatus()));
        detailJobResponse.setStatus(detailJobEntity.getStatus().getName());
        detailJobResponse.setId(detailJobEntity.getId());
        detailJobResponse.setName(detailJobEntity.getName());
        return detailJobResponse;
    }
    public static DetailJobEntity DetailRequestToDetailEntity (DetailJobRequest detailJobRequest){
        DetailJobEntity detailJobEntity = new DetailJobEntity();
        detailJobEntity.setName(detailJobRequest.getName());
        return detailJobEntity;
    }

}
