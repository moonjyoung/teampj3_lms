package com.greenart.lms_service.repository.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.score.ScoreCateEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;

public interface ScoreStandardRepository extends JpaRepository<ScoreStandardEntity, Long> {
    public ScoreStandardEntity findByLectureInfoAndScoreCate(LectureInfoEntity lectureInfo, ScoreCateEntity scoreCate);
    public List<ScoreStandardEntity> findByLectureInfoOrderByScoreCate(LectureInfoEntity lectureInfo);
}
