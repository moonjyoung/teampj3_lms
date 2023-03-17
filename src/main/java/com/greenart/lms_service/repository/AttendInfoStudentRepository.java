package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.AttendInfoStudentEntity;

public interface AttendInfoStudentRepository extends JpaRepository<AttendInfoStudentEntity, Long> {
    
}
