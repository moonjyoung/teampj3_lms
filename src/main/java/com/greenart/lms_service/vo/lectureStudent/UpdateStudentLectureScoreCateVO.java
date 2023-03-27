package com.greenart.lms_service.vo.lectureStudent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class UpdateStudentLectureScoreCateVO {
    @Schema(description = "과제 항목번호", example = "1")
    private Long scoreCateSeq;
    @Schema(description = "학생점수", example = "50")
    private Integer cateStuScore;
}
