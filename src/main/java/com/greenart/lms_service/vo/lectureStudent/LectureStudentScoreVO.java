package com.greenart.lms_service.vo.lectureStudent;

import com.greenart.lms_service.vo.BasicResponse;

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
public class LectureStudentScoreVO {
    //extends BasicResponse 
    // private String liName;  // 강의이름
    // private String liCode; // 강의코드
    private Long seq; //학생번호
    private String stuName; // 학생이름
    private String stuId;    // 학번
    private String stuSubject; // 학생전공
    private Integer stuGrade;  // 학년
    // 출석vo 현재출석/토탈출석
    // 성적vo 
    // 총점vo

    public LectureStudentScoreVO(LectureStudentDAO lecStuDao) {
        this.seq = lecStuDao.getMbSeq(); 
        this.stuName = lecStuDao.getStuName();
        this.stuId = lecStuDao.getMbId();
        this.stuSubject = lecStuDao.getStuSubject();
        this.stuGrade = lecStuDao.getStuGrade();
    }
}
