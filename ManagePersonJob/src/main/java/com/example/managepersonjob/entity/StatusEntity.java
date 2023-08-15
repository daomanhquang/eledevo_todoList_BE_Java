package com.example.managepersonjob.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.Data;
import lombok.ToString;

import java.util.List;

@Entity
@Table(name = "trang_thai")
@Data

public class StatusEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(mappedBy = "status",cascade = CascadeType.ALL)
    @JsonIgnore
//    @ToString.Exclude
    private List<DetailJobEntity> detailJobs;
}
