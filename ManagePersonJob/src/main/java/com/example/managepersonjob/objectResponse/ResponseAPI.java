package com.example.managepersonjob.objectResponse;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseAPI {
    private int status;
    private String message;
    private List<List<?>> content;
}
