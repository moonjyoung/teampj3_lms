package com.greenart.lms_service.repository.score;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.score.ScoreCateEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;
import com.greenart.lms_service.vo.finalGrade.view.TotalMaxScore;

public interface ScoreStandardRepository extends JpaRepository<ScoreStandardEntity, Long> {
    ScoreStandardEntity findByLectureInfoAndScoreCate(LectureInfoEntity lectureInfo,ScoreCateEntity scoreCate );
    public List<ScoreStandardEntity> findByLectureInfoOrderByScoreCate(LectureInfoEntity lectureInfo);

    @Query(value = "select sum(if(a.lectureInfo.liSeq = :liSeq, a.ssScoreMax, 0)) as totalMaxScore from ScoreStandardEntity a")
    TotalMaxScore findByLectureInfo(@Param("liSeq") Long liSeq);

}
