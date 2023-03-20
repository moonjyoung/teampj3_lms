package com.greenart.lms_service.repository.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.score.ScoreMasterEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;

public interface ScoreMasterRepository extends JpaRepository<ScoreMasterEntity, Long> {
    //public ScoreMasterEntity findBySmasSsSeq(ScoreStandardEntity scoreStandard);
    //ScoreMasterEntity findByScoreStandard(ScoreMasterEntity scoreStandard);

    List<ScoreMasterEntity> findByScoreStandard (ScoreStandardEntity scoreStandard);
}
