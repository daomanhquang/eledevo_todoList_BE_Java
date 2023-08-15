package com.example.managepersonjob.repository;

import com.example.managepersonjob.entity.JobEntity;
import com.example.managepersonjob.entity.StatusEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository

public interface StatusRepository extends JpaRepository<StatusEntity, Long> {
    @Query("select status from StatusEntity status where " +
            "status.name like concat('%',:query,'%') ")
    Page<StatusEntity> searchStatus (String query, Pageable pageable);

    // total search
    @Query("select status from StatusEntity status where " +
            "status.name like concat('%',:query,'%') ")
    List<StatusEntity> totalSearchStatus (String query);

    @Query(value = "SELECT * FROM trang_thai ORDER BY name asc",nativeQuery = true)
    Page<StatusEntity> sortStatusAsc(Pageable pageable, String sorttype);
    @Query(value = "SELECT * FROM trang_thai ORDER BY name desc ",nativeQuery = true)
    Page<StatusEntity> sortStatusDesc(Pageable pageable, String sorttype);
    @Query("select status from StatusEntity status where " +
            "status.name like concat('%',:query,'%') " +
            "order by status.name asc")
    Page<StatusEntity> sortASCSearchStatus (String query, Pageable pageable);
    @Query("select status from StatusEntity status where " +
            "status.name like concat('%',:query,'%') " +
            "order by status.name desc")
    Page<StatusEntity> sortDESCSearchStatus (String query, Pageable pageable);
}
