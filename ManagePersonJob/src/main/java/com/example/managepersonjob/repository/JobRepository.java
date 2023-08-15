package com.example.managepersonjob.repository;

import com.example.managepersonjob.entity.JobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface JobRepository extends JpaRepository<JobEntity, Long> {
    @Query("select job from JobEntity job where " +
            "job.name like concat('%',:query,'%') ")
    Page<JobEntity> searchJob (String query, Pageable pageable);

    // total search
    @Query("select job from JobEntity job where " +
            "job.name like concat('%',:query,'%') ")
    List<JobEntity> totalSearchJob (String query);

    @Query(value = "SELECT * FROM cong_viec ORDER BY name asc",nativeQuery = true)
    Page<JobEntity> sortJobAsc(Pageable pageable, String sorttype);
    @Query(value = "SELECT * FROM cong_viec ORDER BY name desc ",nativeQuery = true)
    Page<JobEntity> sortJobDesc(Pageable pageable, String sorttype);
    @Query("select job from JobEntity job where " +
            "job.name like concat('%',:query,'%') " +
            "order by job.name asc")
    Page<JobEntity> sortASCSearchJob (String query, Pageable pageable);
    @Query("select job from JobEntity job where " +
            "job.name like concat('%',:query,'%') " +
            "order by job.name desc")
    Page<JobEntity> sortDESCSearchJob (String query, Pageable pageable);
}
