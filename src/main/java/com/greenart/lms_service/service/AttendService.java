package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.AttendInfoStudentEntity;
import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.FinalGradeEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.AttendInfoMasterRepository;
import com.greenart.lms_service.repository.AttendInfoStudentRepository;
import com.greenart.lms_service.repository.ClassDateRepository;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.SemesterInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.score.FinalGradeRepository;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.attend.AttendRequestVO;
import com.greenart.lms_service.vo.attend.AttendMasResponseVO;
import com.greenart.lms_service.vo.attend.AttendResponseVO;
import com.greenart.lms_service.vo.attend.AttendStuResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AttendService {
    private final AttendInfoMasterRepository attendInfoMasterRepository;
    private final AttendInfoStudentRepository attendInfoStudentRepository;
    private final ClassDateRepository classDateRepository;
    private final LectureInfoRepository lectureInfoRepository;
    private final SemesterInfoRepository semesterInfoRepository;
    private final ClassRegisterRepository classRegisterRepository;
    private final StudentRepository studentRepository;
    private final FinalGradeRepository finalGradeRepository;

    public AttendResponseVO getAttendDay(Long liSeq) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        List<StudentEntity> stuList = new ArrayList<>();
        for (ClassRegisterEntity data : classRegisterRepository.findByLectureInfo(lecture)) {
            stuList.add(data.getStudent());
        }
        AttendResponseVO result = new AttendResponseVO();
        List<AttendMasResponseVO> attMasList = new ArrayList<>();
        for (StudentEntity stuData : stuList) {
            AttendMasResponseVO attMas = new AttendMasResponseVO();
            List<AttendStuResponseVO> attStuList = new ArrayList<>();
            for (AttendInfoStudentEntity data : attendInfoStudentRepository.findByStudent(stuData)) {
                AttendStuResponseVO attStu = new AttendStuResponseVO();
                String status = "";
                if (data.getAttendInfoMaster().getLecture()==lecture) {
                    if (data.getAstuStatus()==null) status = "";
                    else if (data.getAstuStatus()==1) status = "O";
                    else if (data.getAstuStatus()==0) status = "X";
                    attStu.setAmasSeq(data.getAttendInfoMaster().getAmasSeq());
                    attStu.setDate(data.getAttendInfoMaster().getAmasDate());
                    attStu.setStatus(status);
                    attStuList.add(attStu);
                }
            }
            attMas.setMbSeq(stuData.getMbSeq());
            attMas.setName(stuData.getMbName());
            attMas.setList(attStuList);
            attMasList.add(attMas);
        }
        result.setStatus(true);
        result.setMessage("조회 성공");
        result.setList(attMasList);

        return result;
    }

    public BasicResponse postAttendAll(Long liSeq, AttendRequestVO data) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        AttendInfoMasterEntity attendMas = attendInfoMasterRepository.findById(data.getAmasSeq()).orElseThrow(() -> new CustomException("강의일을 확인해주세요."));
        if (attendMas.getLecture()!=lecture) throw new CustomException("강의 정보와 강의일 정보가 맞지 않습니다.");

        for (AttendInfoStudentEntity attendStu : attendInfoStudentRepository.findByAttendInfoMaster(attendMas)) {
            attendStu.ChangeStatus(data.getStatus());
            attendInfoStudentRepository.save(attendStu);
        }

        return new BasicResponse(true, "전체 변경 성공");
    }

    public BasicResponse postAttend(Long liSeq, AttendRequestVO data, Long mbSeq) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        StudentEntity student = studentRepository.findById(mbSeq).orElseThrow(() -> new CustomException("존재하지 않는 학생입니다."));
        ClassRegisterEntity classRegi = classRegisterRepository.findByLectureInfoAndStudent(lecture, student);
        if (classRegi==null) throw new CustomException("강의에 학생이 없습니다.");
        AttendInfoMasterEntity attendMas = attendInfoMasterRepository.findById(data.getAmasSeq()).orElseThrow(() -> new CustomException("존재하지 않는 강의일 입니다."));
        AttendInfoStudentEntity attendStu = attendInfoStudentRepository.findByAttendInfoMasterAndStudent(attendMas, student);
        if (attendStu==null) throw new CustomException("올바르지 않은 입력입니다.");
        attendStu.ChangeStatus(data.getStatus());
        attendInfoStudentRepository.save(attendStu);

        return new BasicResponse(true, "변경 성공");
    }

    public void putAttendFGrade(String code) {
        LectureInfoEntity lecture = lectureInfoRepository.findByLiCode(code);
        if (lecture==null) throw new CustomException("존재하지 않는 강의입니다.");
        List<AttendInfoMasterEntity> amasList = attendInfoMasterRepository.findByLecture(lecture);

        List<StudentEntity> stuList = new ArrayList<>();
        for (ClassRegisterEntity data : classRegisterRepository.findByLectureInfo(lecture)) {
            stuList.add(data.getStudent());
        }

        Long allAttendDay = attendInfoMasterRepository.countByLecture(lecture);
        FinalGradeEntity attFGrade = finalGradeRepository.findById(14L).orElseThrow(() -> new CustomException("DB에 문제가 있습니다. 백엔드에 문의주세요."));
        
        for (StudentEntity stuEntity : stuList) {
            Double stuAttendDay = 0.0;
            Integer nullCount = 0;
            for (AttendInfoMasterEntity attEntity : amasList) {
                if (attendInfoStudentRepository.findByAttendInfoMasterAndStudent(attEntity, stuEntity).getAstuStatus()==null) {
                    nullCount += 1;
                }
                else if (attendInfoStudentRepository.findByAttendInfoMasterAndStudent(attEntity, stuEntity).getAstuStatus()==1) {
                    stuAttendDay += 1.0;
                }
            }
            if (nullCount==0 && Math.ceil(allAttendDay*0.7)>stuAttendDay) {
                ClassRegisterEntity entity = classRegisterRepository.findByLectureInfoAndStudent(lecture, stuEntity);
                entity.ChangeAttFGrade(attFGrade);
                classRegisterRepository.save(entity);
            }
        }
    }
}
