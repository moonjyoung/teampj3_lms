package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.ClassDateEntity;
import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.MemberBasicEntity;
import com.greenart.lms_service.entity.member.ProfessorEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.AttendInfoMasterRepository;
import com.greenart.lms_service.repository.ClassDateRepository;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.MemberBasicRepository;
import com.greenart.lms_service.repository.member.ProfessorRepository;
import com.greenart.lms_service.repository.member.StaffRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.utils.ConvertClassDateTime;
import com.greenart.lms_service.vo.lecture.LectureTimeResponseVO;
import com.greenart.lms_service.vo.lecture.LectureTimeVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final LectureInfoRepository lectureInfoRepository;
    private final AttendInfoMasterRepository attendInfoMasterRepository;
    private final ClassDateRepository classDateRepository;
    private final ClassRegisterRepository classRegisterRepository;
    
    public LectureTimeResponseVO getTimeTable(Long mbSeq) {
        Optional<StudentEntity> studentOpt = studentRepository.findById(mbSeq);
        Optional<ProfessorEntity> professorOpt = professorRepository.findById(mbSeq);
        
        LectureTimeResponseVO result = new LectureTimeResponseVO();
        List<LectureTimeVO> resultList = new ArrayList<>();

        if (studentOpt.isPresent()) {
            List<LectureInfoEntity> lectureList = new ArrayList<>();
            for (ClassRegisterEntity entity : classRegisterRepository.findByStudent(studentOpt.get())) {
                lectureList.add(entity.getLectureInfo());
            }
            for (AttendInfoMasterEntity amasEntity : attendInfoMasterRepository.findAll()) {
                for (LectureInfoEntity lecture : lectureList) {
                    for (ClassDateEntity classDateEntity : classDateRepository.findByLecture(lecture)) {
                        if (amasEntity.getAmasDate().getDayOfWeek().getValue()==classDateEntity.getCdWeek()) {
                            LectureTimeVO vo = new LectureTimeVO();
                            vo.setTitle(lecture.getLiName());
                            vo.setType(lecture.getLiCode());
                            vo.setStartDate(ConvertClassDateTime.convertClassDateTime(amasEntity.getAmasDate(), classDateEntity.getCdStart()).toString());
                            vo.setEndDate(ConvertClassDateTime.convertClassDateTime(amasEntity.getAmasDate(), classDateEntity.getCdLast()).minusMinutes(10L).toString());
                            resultList.add(vo);
                        }
                    }
                }
            }
        }
        else if (professorOpt.isPresent()) {
            for (AttendInfoMasterEntity amasEntity : attendInfoMasterRepository.findAll()) {
                for (LectureInfoEntity lecture : lectureInfoRepository.findByProfessor(professorOpt.get())) {
                    for (ClassDateEntity classDateEntity : classDateRepository.findByLecture(lecture)) {
                        if (amasEntity.getAmasDate().getDayOfWeek().getValue()==classDateEntity.getCdWeek()) {
                            LectureTimeVO vo = new LectureTimeVO();
                            vo.setTitle(lecture.getLiName());
                            vo.setType(lecture.getLiCode());
                            vo.setStartDate(ConvertClassDateTime.convertClassDateTime(amasEntity.getAmasDate(), classDateEntity.getCdStart()).toString());
                            vo.setEndDate(ConvertClassDateTime.convertClassDateTime(amasEntity.getAmasDate(), classDateEntity.getCdLast()).minusMinutes(10L).toString());
                            resultList.add(vo);
                        }
                    }
                }
            }
        }
        else {
            throw new CustomException("회원 번호를 확인하세요.");
        }

        result.setStatus(true);
        result.setMessage("조회 성공");
        result.setList(resultList);

        return result;
    }
}
