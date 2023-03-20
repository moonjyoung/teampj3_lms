package com.greenart.lms_service.vo.lectureStudent;

import java.util.ArrayList;
import java.util.List;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.MemberBasicEntity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class LectureStudentListVO {
    // 해당 강의의
    // 학생 학번 , 이름, 학년, 전공명
    private Long lecSeq;
    private String lectureName;
    private List<DownLectureStudentListVO> downVO = new ArrayList<>(); //vo대댓글 처럼 vo 나눠서 리스트로 받기!
    
    // 하위 객체를 받아야 하니까 그걸 받아서 리스트를 띄우는게 있어야함
    public void downVO(List <ClassRegisterEntity> entity) {
        for(ClassRegisterEntity c :entity) {
            downVO.add(new DownLectureStudentListVO(c));
        }
    }

    public LectureStudentListVO(ClassRegisterEntity entity) {
        LectureInfoEntity lecture = entity.getLectureInfo();
        MemberBasicEntity memberStu = entity.getStudent();

        this.lecSeq = entity.getLectureInfo().getLiSeq();
        this.lectureName = entity.getLectureInfo().getLiName();

    }
    
}
