package com.greenart.lms_service.service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.AttendInfoStudentEntity;
import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.AttendInfoMasterRepository;
import com.greenart.lms_service.repository.AttendInfoStudentRepository;
import com.greenart.lms_service.repository.ClassDateRepository;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.SemesterInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.vo.BasicResponse;
import com.greenart.lms_service.vo.attend.AttendAllDayRequestVO;
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

    public List<AttendResponseVO> getAttendDay(Long liSeq) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        List<StudentEntity> stuList = new ArrayList<>();
        for (ClassRegisterEntity data : classRegisterRepository.findByLectureInfo(lecture)) {
            stuList.add(data.getStudent());
        }
        List<AttendResponseVO> resultList = new ArrayList<>();
        for (StudentEntity stuData : stuList) {
            AttendResponseVO attMas = new AttendResponseVO();
            List<AttendStuResponseVO> attStuList = new ArrayList<>();
            for (AttendInfoStudentEntity data : attendInfoStudentRepository.findByStudent(stuData)) {
                AttendStuResponseVO attStu = new AttendStuResponseVO();
                String status = "";
                if (data.getAstuStatus()==null) status = "";
                else if (data.getAstuStatus()==1) status = "O";
                else if (data.getAstuStatus()==0) status = "X";
                attStu.setAmasSeq(data.getAttendInfoMaster().getAmasSeq());
                attStu.setDate(data.getAttendInfoMaster().getAmasDate());
                attStu.setStatus(status);
                attStuList.add(attStu);
            }
            attMas.setStatus(true);
            attMas.setMessage("조회 성공");
            attMas.setMbSeq(stuData.getMbSeq());
            attMas.setName(stuData.getMbName());
            attMas.setList(attStuList);
            resultList.add(attMas);
        }

        return resultList;
    }

    public BasicResponse patchAttendAll(Long liSeq, AttendAllDayRequestVO request) {
        Integer aStatus = request.getAStatus();
        LocalDate date = request.getDate();
        System.out.println("+++++"+date);
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        AttendInfoMasterEntity data = attendInfoMasterRepository.findByLectureAndAmasDate(lecture, date);
        if (data==null) throw new CustomException("강의일을 확인하세요.");
        for (AttendInfoStudentEntity data2 : attendInfoStudentRepository.findByAttendInfoMaster(data)) {
            data2.ChangeStatus(aStatus);
            attendInfoStudentRepository.save(data2);
        }

        return new BasicResponse(true, "전체 변경 성공");
    }
}
