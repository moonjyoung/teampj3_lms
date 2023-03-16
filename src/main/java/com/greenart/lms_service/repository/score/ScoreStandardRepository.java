package com.greenart.lms_service.repository.score;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.score.ScoreStandardEntity;

public interface ScoreStandardRepository extends JpaRepository<ScoreStandardEntity, Long> {
    ScoreStandardEntity findBySsLiSeqAndSsScSeq(Long ssLiSeq, Long ssScSeq);
}
