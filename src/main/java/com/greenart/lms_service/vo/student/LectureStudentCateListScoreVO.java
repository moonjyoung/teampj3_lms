package com.greenart.lms_service.vo.student;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureStudentCateListScoreVO {

    @Schema(description = "과제 항목번호", example = "1")
    private Long scoreCateSeq;
    @Schema(description = "과제 항목이름", example = "중간시험")
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
