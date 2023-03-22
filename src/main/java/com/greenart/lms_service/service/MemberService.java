package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.ClassDateEntity;
import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.MemberBasicEntity;
import com.greenart.lms_service.entity.member.ProfessorEntity;
import com.greenart.lms_service.entity.member.StaffEntity;
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
import com.greenart.lms_service.security.provider.JwtTokenProvider;
import com.greenart.lms_service.security.service.CustomUserDetailService;
import com.greenart.lms_service.utils.ConvertClassDateTime;
import com.greenart.lms_service.vo.lecture.LectureTimeResponseVO;
import com.greenart.lms_service.vo.lecture.LectureTimeVO;
import com.greenart.lms_service.vo.member.MemberLoginResponseVO;
import com.greenart.lms_service.vo.member.MemberLoginVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MemberService {
    private final AuthenticationManagerBuilder authBuilder;
    private final JwtTokenProvider tokenProvider;
    private final CustomUserDetailService userDetailService;

    private final StudentRepository studentRepository;
    private final ProfessorRepository professorRepository;
    private final StaffRepository staffRepository;
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

    public MemberLoginResponseVO postLogin(MemberLoginVO data) {
        StudentEntity student = studentRepository.findByMbIdAndMbPwd(data.getId(), data.getPwd());
        ProfessorEntity professor = professorRepository.findByMbIdAndMbPwd(data.getId(), data.getPwd());
        StaffEntity staff = staffRepository.findByMbIdAndMbPwd(data.getId(), data.getPwd());
        
        MemberLoginResponseVO result = new MemberLoginResponseVO();
        
        if (student!=null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(student.getMbId(), student.getMbPwd());
            Authentication authentication = authBuilder.getObject().authenticate(authenticationToken);
            result = new MemberLoginResponseVO(student);
            result.setToken(tokenProvider.generateToken(authentication));
        }
        else if (professor!=null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(professor.getMbId(), professor.getMbPwd());
            Authentication authentication = authBuilder.getObject().authenticate(authenticationToken);
            result = new MemberLoginResponseVO(professor);
            result.setToken(tokenProvider.generateToken(authentication));
        }
        else if (staff!=null) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(staff.getMbId(), staff.getMbPwd());
            Authentication authentication = authBuilder.getObject().authenticate(authenticationToken);
            result = new MemberLoginResponseVO(staff);
            result.setToken(tokenProvider.generateToken(authentication));
        }
        else {
            throw new CustomException("로그인에 실패했습니다.");
        }

        result.setStatus(true);
        result.setMessage("로그인 성공");

        return result;
    }
}
