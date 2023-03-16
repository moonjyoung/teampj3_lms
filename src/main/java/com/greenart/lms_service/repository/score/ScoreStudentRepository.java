package com.greenart.lms_service.repository.score;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.score.ScoreStudentEntity;

public interface ScoreStudentRepository extends JpaRepository<ScoreStudentEntity, Long> {
    
}
