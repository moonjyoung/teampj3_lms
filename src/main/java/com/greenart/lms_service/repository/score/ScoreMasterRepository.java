package com.greenart.lms_service.repository.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.score.ScoreMasterEntity;

public interface ScoreMasterRepository extends JpaRepository<ScoreMasterEntity, Long> {
    List<ScoreMasterEntity> findBySmasSsSeq(Long smasSsSeq);
}
