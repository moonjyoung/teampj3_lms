package com.greenart.lms_service.repository.score;

import com.greenart.lms_service.vo.finalGrade.view.RankScore;
import com.greenart.lms_service.vo.finalGrade.view.TotalScore;


import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.ScoreMasterEntity;
import com.greenart.lms_service.entity.score.ScoreStudentEntity;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ScoreStudentRepository extends JpaRepository<ScoreStudentEntity, Long> {
    public ScoreStudentEntity findByScoreMasterAndStudent(ScoreMasterEntity scoreMaster, StudentEntity student);
    @Query(value = "select sum(if(c.mbSeq = :student and d.lectureInfo.liSeq = :lecture, a.sstuScore, 0)) as totalScore from ScoreStudentEntity a join a.scoreMaster b join a.student c join b.scoreStandard d")
    TotalScore findByMbSeq(@Param("student") Long mbSeq, @Param("lecture") Long liSeq);
    @Query(value = "select sum(if(d.lectureInfo.liSeq = :lecture, a.sstuScore, 0)) as studentScore, rank() over (order by sum(if(d.lectureInfo.liSeq = :lecture, a.sstuScore, 0)) desc) as studentRank, c.mbId as studentCode from ScoreStudentEntity a join a.scoreMaster b join a.student c join b.scoreStandard d group by c.mbId")
    List<RankScore> findByLiSeq(@Param("lecture") Long liSeq);




}
