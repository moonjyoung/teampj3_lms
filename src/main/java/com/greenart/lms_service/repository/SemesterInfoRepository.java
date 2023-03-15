package com.greenart.lms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.SemesterInfoEntity;

public interface SemesterInfoRepository extends JpaRepository<SemesterInfoEntity, Long> {
    
}
