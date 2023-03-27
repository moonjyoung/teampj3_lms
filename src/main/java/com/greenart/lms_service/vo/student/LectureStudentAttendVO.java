package com.greenart.lms_service.vo.student;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class LectureStudentAttendVO {
    // 강의에서
        //  - 상위카테고리번호 카테고리이름(출석), 출석날짜, 전체날짜

    // private Long scoreCateSeq;
    // private String scoreCateName;
    // private Long liSeq;
    // private Long mbSeq;
    @Schema(description = "출석일", example = "19")
    private Integer attendCount;
    @Schema(description = "전체출석일", example = "29")
    private Integer attendCountTotal;

    public LectureStudentAttendVO(LectureStudentAttendVoVIEW entity) {
        // this.liSeq = entity.getLiSeq();
        // this.mbSeq = entity.getMbSeq();
        this.attendCount = entity.getAttendCount();
        this.attendCountTotal = entity.getAttendCountTotal();
    }

}
