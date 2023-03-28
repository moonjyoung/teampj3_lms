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
import com.greenart.lms_service.entity.SemesterInfoEntity;
import com.greenart.lms_service.entity.member.MemberBasicEntity;
import com.greenart.lms_service.entity.member.ProfessorEntity;
import com.greenart.lms_service.entity.member.StaffEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.view.TimetableProfessorView;
import com.greenart.lms_service.entity.view.TimetableStudentView;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.AttendInfoMasterRepository;
import com.greenart.lms_service.repository.ClassDateRepository;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.SemesterInfoRepository;
import com.greenart.lms_service.repository.member.MemberBasicRepository;
import com.greenart.lms_service.repository.member.ProfessorRepository;
import com.greenart.lms_service.repository.member.StaffRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.view.TimetableProfessorViewRepository;
import com.greenart.lms_service.repository.view.TimetableStudentViewRepository;
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
    private final SemesterInfoRepository semesterInfoRepository;
    
    private final TimetableStudentViewRepository timetableStudentViewRepository;
    private final TimetableProfessorViewRepository timetableProfessorViewRepository;
    
    public LectureTimeResponseVO getTimeTable(Long mbSeq, Long siSeq) {
        Optional<StudentEntity> studentOpt = studentRepository.findById(mbSeq);
        Optional<ProfessorEntity> professorOpt = professorRepository.findById(mbSeq);
        SemesterInfoEntity semester = semesterInfoRepository.findById(siSeq).orElseThrow(() -> new CustomException("학기정보를 확인해주세요."));

        LectureTimeResponseVO result = new LectureTimeResponseVO();
        List<LectureTimeVO> resultList = new ArrayList<>();

        if (studentOpt.isPresent()) {
            for (TimetableStudentView view : timetableStudentViewRepository.findByMbSeqAndSiSeq(mbSeq, siSeq)) {
                LectureTimeVO vo = new LectureTimeVO();
                vo.setTitle(view.getLiName());
                vo.setType(view.getLiCode());
                vo.setStartDate(ConvertClassDateTime.convertClassDateTime(view.getAmasDate(), view.getCdStart()).toString());
                vo.setEndDate(ConvertClassDateTime.convertClassDateTime(view.getAmasDate(), view.getCdLast()).plusMinutes(50L).toString());
                resultList.add(vo);
            }
        }
        else if (professorOpt.isPresent()) {
            for (TimetableProfessorView view : timetableProfessorViewRepository.findByMbSeqAndSiSeq(mbSeq, siSeq)) {
                LectureTimeVO vo = new LectureTimeVO();
                vo.setTitle(view.getLiName());
                vo.setType(view.getLiCode());
                vo.setStartDate(ConvertClassDateTime.convertClassDateTime(view.getAmasDate(), view.getCdStart()).toString());
                vo.setEndDate(ConvertClassDateTime.convertClassDateTime(view.getAmasDate(), view.getCdLast()).plusMinutes(50L).toString());
                resultList.add(vo);
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
