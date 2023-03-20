package com.greenart.lms_service.vo.lectureStudent;

import com.greenart.lms_service.entity.ClassRegisterEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DownLectureStudentListVO {
    private String stuId;
    private String stuName;
    private Integer stuGrade;
    private String stuSubject;

    public DownLectureStudentListVO(ClassRegisterEntity entity) {
        this.stuId = entity.getStudent().getMbId();
        this.stuName = entity.getStudent().getMbName();
        this.stuGrade = entity.getStudent().getStuGrade();
        this.stuSubject = entity.getStudent().getStuSubject();
    }
}
