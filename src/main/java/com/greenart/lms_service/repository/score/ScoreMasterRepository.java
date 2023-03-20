package com.greenart.lms_service.repository.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenart.lms_service.entity.score.ScoreMasterEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;

public interface ScoreMasterRepository extends JpaRepository<ScoreMasterEntity, Long> {
    public List<ScoreMasterEntity> findByScoreStandard(ScoreStandardEntity scoreStandard);
}
