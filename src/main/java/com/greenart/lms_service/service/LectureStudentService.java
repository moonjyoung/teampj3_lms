package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentListVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureStudentService {
    private final ClassRegisterRepository crRepo;	
    private final LectureInfoRepository lecRepo;
    private final StudentRepository stuRepo;	

    // 교수번호(P타입-repo)- (수강신청)강의entity-학생mb_entity(S타입)-stu_entity(전공명)	
    // seq, 학번 , 이름, 학년, 전공명	
    // public List<LectureStudentListVO> lectureStudentList(Long liSeq) {	
    //     LectureInfoEntity lecture = lecRepo.findById(liSeq).orElse(null);	
    //     List<ClassRegisterEntity> entity = crRepo.findByLectureInfo(lecture);	

    //     List<LectureStudentListVO> lectureVO = new ArrayList<>();

    //     for(ClassRegisterEntity s : entity) {	
    //         LectureStudentListVO lecVO = new LectureStudentListVO(s);	
    //         List<ClassRegisterEntity> crList = crRepo.findByClassRegister(s);	
    //         lecVO.downVO(crList);	
    //         lectureVO.add(lecVO);;	
    //     }	
    //     return lectureVO;	
    // }
}
