package com.greenart.lms_service.repository.score;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.score.FinalGradeEntity;

public interface FinalGradeRepository extends JpaRepository<FinalGradeEntity, Long> {
    FinalGradeEntity findByFgSeq(Long fgSeq);
}
