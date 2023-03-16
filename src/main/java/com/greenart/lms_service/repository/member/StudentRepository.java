package com.greenart.lms_service.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.member.StudentEntity;

public interface StudentRepository extends JpaRepository<StudentEntity, Long> {
    
}
