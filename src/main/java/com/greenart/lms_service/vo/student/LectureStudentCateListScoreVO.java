package com.greenart.lms_service.vo.student;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureStudentCateListScoreVO {
    // 강의에서
    //  - 상위카테고리번호 카테고리이름(중간), 점수 , 최대점수
    //  - 상위카테고리번호 카테고리이름(기말), 점수 , 최대점수
    //  - 상위카테고리번호 카테고리이름(과제1), 점수 , 최대점수
    @Schema(description = "카테고리번호", example = "1")
    private Long scoreCateSeq;
    @Schema(description = "카테고리이름", example = "중간시험")
    private String scoreCateName;
    @Schema(description = "학생점수", example = "50")
    private Integer score;
    @Schema(description = "기준점수", example = "100")
    private Integer maxScore;

    public LectureStudentCateListScoreVO(LectureStudentCateListScoreVoVIEW entity) {
        this.scoreCateSeq = entity.getSmasSeq();
        this.scoreCateName = entity.getSmasName();
        this.score = entity.getSstuScore();
        this.maxScore = entity.getSmasScore();
    }
}
