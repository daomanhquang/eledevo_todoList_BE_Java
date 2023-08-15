package com.example.managepersonjob.objectRequest;

import lombok.Data;

import java.util.List;

@Data
public class DetailJobRequest {
    private String name;
    private Long idJob;
    private Long idStatus;
}
