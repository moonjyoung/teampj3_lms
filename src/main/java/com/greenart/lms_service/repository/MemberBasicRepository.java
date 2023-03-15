package com.greenart.lms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.MemberBasicEntity;

public interface MemberBasicRepository<T extends MemberBasicEntity> extends JpaRepository<T, Long> {
    
}
