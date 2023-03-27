package com.greenart.lms_service.vo.lectureStudent;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class LectureStudentDaoVO {
    @Schema(description = "학생고유번호", example = "1")
    private Long seq;
    @Schema(description = "학생이름", example = "강백호")
    private String stuName;
    @Schema(description = "학번", example = "2023123001")
    private String stuId;
    @Schema(description = "학과", example = "컴퓨터공학")
    private String stuSubject;
    @Schema(description = "학년", example = "1")
    private Integer stuGrade; 

    public LectureStudentDaoVO(LectureStudentDAO lecStuDao) { // LectureStudentDAO view Entity(복합키걸려있음)
        this.seq = lecStuDao.getMbSeq();
        this.stuName = lecStuDao.getStuName();
        this.stuId = lecStuDao.getMbId();
        this.stuSubject = lecStuDao.getStuSubject();
        this.stuGrade = lecStuDao.getStuGrade();
    }
}
