package com.greenart.lms_service.repository.score;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.score.ScoreCateEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;

public interface ScoreStandardRepository extends JpaRepository<ScoreStandardEntity, Long> {
    public ScoreStandardEntity findByLectureInfoAndScoreCate(LectureInfoEntity lectureInfo,ScoreCateEntity scoreCate);
   
    
    
    

}
