package com.greenart.lms_service.vo;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.score.ScoreMasterEntity;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AllLectureResponse {
    @Schema (description = "강의 기본키" , example = "1")
    private Long liSeq;
    @Schema (description = "강의 이름" , example = "자바기초")
    private String liName;
    @Schema (description = "교수 이름" , example = "홍길동")
    private String professorName;
    @Schema (description = "출석 만점" , example = "100")
    private Integer attendRatio;
    @Schema (description = "과제 만점" , example = "100")
    private Integer assignmentRaio;
    @Schema (description = "중간 만점" , example = "100")
    private Integer middleRatio;
    @Schema (description = "기말 만점" , example = "100")
    private Integer finalRatio;
    @Schema (description = "평가 방식 (1:절대, 2:상대)" , example = "1")
    private Integer liEvaluationType;

    private ScoreMasterEntity smEntity;
    public AllLectureResponse(LectureInfoEntity entity){
        this.liSeq = entity.getLiSeq();
        this.liName = entity.getLiName();
        this.professorName = entity.getProfessor().getMbName();
        this.attendRatio = smEntity.getScoreStandard().getSsScoreMax();
        this.assignmentRaio = smEntity.getScoreStandard().getSsScoreMax();
        this.middleRatio = smEntity.getScoreStandard().getSsScoreMax();
        this.finalRatio = smEntity.getScoreStandard().getSsScoreMax();
        this.liEvaluationType = entity.getLiEvaluation_type();
    }
}
