package com.greenart.lms_service;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.lms_service.repository.AttendInfoStudentRepository;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.score.ScoreStudentRepository;
import com.greenart.lms_service.repository.viewRepo.LecturestudentdaoRepository;
import com.greenart.lms_service.service.LectureStudentService;


@SpringBootTest
class hyeonjuTest {
    @Autowired ClassRegisterRepository crRepo;
    @Autowired LectureStudentService lecService;
    @Autowired LectureInfoRepository lecRepo;
    @Autowired StudentRepository stuRepo;
    @Autowired ScoreStudentRepository scoreRepo;
    @Autowired AttendInfoStudentRepository attendstuRepo;
    @Autowired LecturestudentdaoRepository lecstuDaoRepo;

    @Test
    void 내강의학생조회() {
        
    }

    // @Test
    // void 쿼리확인(){
    //     // Long stuSeq = 1L;
    //     // Integer status = 1;
    //     // StudentAttendVO vo = lecstuDaoRepo.findByStudentAttend(stuSeq, status);

    //     // System.out.println(vo.getMbSeq());
    // }


}
