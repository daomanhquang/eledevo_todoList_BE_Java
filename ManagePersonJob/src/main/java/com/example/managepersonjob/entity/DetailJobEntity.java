package com.example.managepersonjob.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
@Table(name = "chi_tiet_cong_viec")
public class DetailJobEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "status_id")
    private StatusEntity status;
    @ManyToOne
    @JoinColumn(name = "job_id")
    private JobEntity job;


}
