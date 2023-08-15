package com.example.managepersonjob.objectResponse;

import com.example.managepersonjob.entity.JobEntity;
import com.example.managepersonjob.entity.StatusEntity;
import lombok.Data;

@Data
public class DetailJobResponse {
    private Long id;
    private String name;
    private String status;

}
