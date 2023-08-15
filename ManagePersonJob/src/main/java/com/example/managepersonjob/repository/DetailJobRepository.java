package com.example.managepersonjob.repository;

import com.example.managepersonjob.entity.DetailJobEntity;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface DetailJobRepository extends JpaRepository<DetailJobEntity, Long> {
    @Query("select detail from DetailJobEntity detail where detail.job.id = :idJob")
    Page<DetailJobEntity> getPage(Long idJob, Pageable pageable);

    @Query("select detail from DetailJobEntity detail where detail.job.id = :idJob")
    List<DetailJobEntity> getListDetail(Long idJob);

    @Query("select detailJob from DetailJobEntity detailJob where " +
            "detailJob.name like concat('%',:query,'%') and detailJob.job.id = :idJob ")
    Page<DetailJobEntity> searchDetailJob(String query, Pageable pageable,Long idJob);

    // total search
//    @Query("select detailJob from DetailJobEntity detailJob where detailJob.job.id = :idJob " +
//            "and detailJob.name like concat('%',:query,'%')   ")
    @Query(value = "select * from chi_tiet_cong_viec ctcv where ctcv.job_id = ?1 and ctcv.name like concat('%',?2,'%')", nativeQuery = true)
    List<DetailJobEntity> totalSearchDetailJob(Long idJob,String query);

    @Query(value = "select * from chi_tiet_cong_viec ctcv where ctcv.job_id = ?1  ORDER BY name asc  ", nativeQuery = true)
    Page<DetailJobEntity> sortDetailJobAsc(Long idJob, String sorttype,Pageable pageable);

    @Query(value = "SELECT * FROM chi_tiet_cong_viec where chi_tiet_cong_viec.job_id = ?1 ORDER BY name desc  ", nativeQuery = true)
    Page<DetailJobEntity> sortDetailJobDesc(Long idJob, Pageable pageable, String sorttype);

    @Query("select detailJob from DetailJobEntity detailJob where " +
            "detailJob.name like concat('%',:query,'%') and detailJob.job.id = :idJob" +
            " order by detailJob.name asc ")
    Page<DetailJobEntity> sortASCSearchDetailJob(String query, Pageable pageable,Long idJob);

    @Query("select detailJob from DetailJobEntity detailJob where " +
            "detailJob.name like concat('%',:query,'%') and detailJob.job.id = :idJob " +
            "order by detailJob.name desc")
    Page<DetailJobEntity> sortDESCSearchDetailJob(String query, Pageable pageable,Long idJob);

}
