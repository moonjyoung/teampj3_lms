package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.ClassDateEntity;

public interface ClassDateRepository extends JpaRepository<ClassDateEntity, Long> {
    public List<ClassDateEntity> findByCdLiSeq(Long cdLiSeq);
}
