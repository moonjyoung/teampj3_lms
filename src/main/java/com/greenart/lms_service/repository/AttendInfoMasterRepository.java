package com.greenart.lms_service.repository;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;

public interface AttendInfoMasterRepository extends JpaRepository<AttendInfoMasterEntity, Long>{
    public List<AttendInfoMasterEntity> findByLecture(LectureInfoEntity lecture);
    public AttendInfoMasterEntity findByLectureAndAmasDate(LectureInfoEntity lecture, LocalDate amasDate);
    
}
