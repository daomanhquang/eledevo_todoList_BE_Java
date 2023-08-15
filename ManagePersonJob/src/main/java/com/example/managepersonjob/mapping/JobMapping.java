package com.example.managepersonjob.mapping;

import com.example.managepersonjob.entity.DetailJobEntity;
import com.example.managepersonjob.entity.JobEntity;
import com.example.managepersonjob.objectRequest.JobRequest;
import com.example.managepersonjob.objectResponse.DetailJobResponse;
import com.example.managepersonjob.objectResponse.JobResponse;
import lombok.Data;
import org.springframework.web.bind.annotation.Mapping;

import java.util.ArrayList;
import java.util.List;
@Data
public class JobMapping {
    public static JobEntity JobRequestToJobEntity (JobRequest jobRequest){
        JobEntity jobEntity = new JobEntity();
        jobEntity.setName(jobRequest.getName());
        return jobEntity;
    }
    public static JobResponse JobEntityToJobResponse(JobEntity jobEntity){
        JobResponse jobResponse = new JobResponse();
        jobResponse.setId(jobEntity.getId());
        jobResponse.setName(jobEntity.getName());
        jobResponse.setProcess(jobEntity.getProcess());
        List<DetailJobResponse> detailJobResponses=new ArrayList<DetailJobResponse>() ;
        for(DetailJobEntity detailJobEntity: jobEntity.getDetailJobEntities()){
            detailJobResponses.add(DetailJobMapping.DetailEntityToDetailResponse(detailJobEntity));
        }
         return jobResponse;
    }
}
