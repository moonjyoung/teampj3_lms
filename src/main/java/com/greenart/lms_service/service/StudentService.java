package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.SemesterInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.view.StudentSemesterGradeView;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.SemesterInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.view.StudentSemesterGradeViewRepository;
import com.greenart.lms_service.vo.student.AllGradeResponseVO;
import com.greenart.lms_service.vo.student.StudentAllGradeResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StudentService {
    private final StudentRepository studentRepository;
    private final SemesterInfoRepository semesterInfoRepository;
    private final StudentSemesterGradeViewRepository studentSemesterGradeViewRepository;

    public StudentAllGradeResponseVO getMyGrade(Long mbSeq, Long siSeq) {
        StudentEntity student = studentRepository.findById(mbSeq).orElseThrow(() -> new CustomException("존재하지 않는 학생입니다."));
        SemesterInfoEntity semester = semesterInfoRepository.findById(siSeq).orElseThrow(() -> new CustomException("학기 정보를 확인해주세요."));
        
        StudentAllGradeResponseVO result = new StudentAllGradeResponseVO();
        result.setMbSeq(student.getMbSeq());
        result.setName(student.getMbName());
        result.setId(student.getMbId());
        result.setMajor(student.getStuSubject());
        result.setGrade(student.getStuGrade());
        Double total3Sum = 0.0;
        Double total5Sum = 0.0;
        Integer count = 0;
        List<AllGradeResponseVO> resultList = new ArrayList<>();

        for (StudentSemesterGradeView data : studentSemesterGradeViewRepository.findByMbSeqAndLiSiSeq(mbSeq, siSeq)) {
            AllGradeResponseVO resultGrade = new AllGradeResponseVO();
            resultGrade.setCode(data.getLiCode());
            resultGrade.setName(data.getLiName());
            resultGrade.setGrade(data.getLiGrade());
            resultGrade.setFgName(data.getFgName());
            resultGrade.setFg3(data.getFgPoint3());
            resultGrade.setFg5(data.getFgPoint5());
            resultList.add(resultGrade);
            total3Sum += data.getFgPoint3()*data.getLiGrade();
            total5Sum += data.getFgPoint5()*data.getLiGrade();
            count += data.getLiGrade();
        }
        result.setTotal3(((int)Math.round(total3Sum/count*100))/100.0);
        result.setTotal5(((int)Math.round(total5Sum/count*100))/100.0);
        result.setList(resultList);

        result.setStatus(true);
        result.setMessage("조회 성공");

        return result;
    }
}
