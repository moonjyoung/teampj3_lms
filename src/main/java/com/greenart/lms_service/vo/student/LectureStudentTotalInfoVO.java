package com.greenart.lms_service.vo.student;

import java.util.List;

import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureStudentTotalInfoVO {
    @Schema(description = "학번", example = "2023123001")
    private String stuId;
    @Schema(description = "학생이름", example = "강백호")
    private String stuName;
    @Schema(description = "학년", example = "1")
    private Integer stuGrade;
    @Schema(description = "학과", example = "컴퓨터공학")
    private String stuSubject;
    @Schema(description = "최종점수", example = "미평가 or A+")
    private String finalScore;
    
    //  - 상위카테고리번호 카테고리이름(중간), 점수, 최대점수
    @Schema(description = "카테고리 항목별점수")
    private List<LectureStudentCateListScoreVO> lectureStudentCateListScoreVO;
    //  - 상위카테고리번호 카테고리이름(출석), 출석날짜, 전체날짜 (카테고리는 성적master의 정보)
    @Schema(description = "출석/전체출석기준")
    private List<LectureStudentAttendVO> lectureStudentAttendVO;
    
        

}
