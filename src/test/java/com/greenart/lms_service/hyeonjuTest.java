package com.greenart.lms_service;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.service.LectureStudentService;

@SpringBootTest
class hyeonjuTest {
    @Autowired ClassRegisterRepository crRepo;
    @Autowired LectureStudentService lecService;
    @Autowired LectureInfoRepository lecRepo;
    @Autowired StudentRepository stuRepo;

    @Test
    void 내강의학생조회() {

    }

}
