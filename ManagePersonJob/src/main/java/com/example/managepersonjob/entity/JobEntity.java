package com.example.managepersonjob.entity;

import com.example.managepersonjob.repository.JobRepository;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
@Table(name = "cong_viec")
@Data
public class JobEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String process ="0/0";
    @OneToMany (mappedBy = "job",cascade = CascadeType.ALL)
    List<DetailJobEntity> detailJobEntities;

}
