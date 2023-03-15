package com.greenart.lms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    
}
