package com.greenart.lms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.LectureInfoEntity;

public interface LectureInfoRepository extends JpaRepository<LectureInfoEntity, Long> {
    
}
