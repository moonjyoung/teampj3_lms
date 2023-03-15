package com.greenart.lms_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.ProfessorEntity;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    
}
