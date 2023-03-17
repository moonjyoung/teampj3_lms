package com.greenart.lms_service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.service.LectureStudentService;
import com.greenart.lms_service.vo.LectureStudentListVO;

@SpringBootTest
class hyeonjuTest {
    @Autowired ClassRegisterRepository crRepo;
    @Autowired LectureStudentService lecService;
    @Autowired LectureInfoRepository lecRepo;
    @Autowired StudentRepository stuRepo;

    @Test
    void 내강의학생조회() {
        // 테스트 실패 다시 해야함
        LectureInfoEntity lec = lecRepo.findById(1L).orElseThrow(); // 강의번호 1번
        List<ClassRegisterEntity> classRegister = crRepo.findByLectureInfo(lec); // 수강신청에 1번을 넣고
        
        // List<StudentEntity> student = new ArrayList<>();
        List<LectureStudentListVO> lectureVO = new ArrayList<>();

        for(ClassRegisterEntity s : classRegister){
            LectureStudentListVO lecVo = new LectureStudentListVO(s);
            List<ClassRegisterEntity> crList = crRepo.findByClassRegister(s);
            lecVo.downVO(crList);
            lectureVO.add(lecVo);
            // student.add(s.getStudent());
        }
        System.out.println(lectureVO);
        // System.out.println(classRegister.get(0).getCrSeq());
        // System.out.println(student.get(0).getMbId());
        
    }

}
