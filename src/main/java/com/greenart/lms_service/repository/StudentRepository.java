package com.greenart.lms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    
}
