package com.greenart.lms_service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.AttendInfoStudentEntity;
import com.greenart.lms_service.entity.ClassDateEntity;
import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.SemesterInfoEntity;
import com.greenart.lms_service.entity.member.MemberBasicEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.ScoreCateEntity;
import com.greenart.lms_service.entity.score.ScoreMasterEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;
import com.greenart.lms_service.entity.score.ScoreStudentEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.AttendInfoMasterRepository;
import com.greenart.lms_service.repository.AttendInfoStudentRepository;
import com.greenart.lms_service.repository.ClassDateRepository;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.SemesterInfoRepository;
import com.greenart.lms_service.repository.member.MemberBasicRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.score.ScoreCateRepository;
import com.greenart.lms_service.repository.score.ScoreMasterRepository;
import com.greenart.lms_service.repository.score.ScoreStandardRepository;
import com.greenart.lms_service.repository.score.ScoreStudentRepository;
import com.greenart.lms_service.vo.attend.AttendResponseVO;
import com.greenart.lms_service.vo.attend.AttendStuResponseVO;
import com.greenart.lms_service.vo.score.ScoreMasResponseVO;
import com.greenart.lms_service.vo.score.ScoreResponseVO;
import com.greenart.lms_service.vo.score.ScoreStuResponseVO;

import jakarta.transaction.Transactional;

@SpringBootTest
class AttendServiceApplicationTests {
	@Autowired private AttendInfoMasterRepository attendInfoMasterRepository;
    @Autowired private AttendInfoStudentRepository attendInfoStudentRepository;
    @Autowired private ClassDateRepository classDateRepository;
    @Autowired private LectureInfoRepository lectureInfoRepository;
    @Autowired private SemesterInfoRepository semesterInfoRepository;
    @Autowired private ClassRegisterRepository classRegisterRepository;
    @Autowired private MemberBasicRepository memberBasicRepository;
    @Autowired private StudentRepository studentRepository;
    @Autowired private ScoreCateRepository scoreCateRepository;
    @Autowired private ScoreStandardRepository scoreStandardRepository;
    @Autowired private ScoreMasterRepository scoreMasterRepository;
    @Autowired private ScoreStudentRepository scoreStudentRepository;


	@Test // 강의정보, 학기정보, 강의 수업요일정보 입력되어 있을때 해당학기 해당강의 출결일 자동입력 코드
	void putAttendDay() {
        LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        SemesterInfoEntity semester = lecture.getSemesterInfoEntity();
        List<Integer> weekdayList = new ArrayList<>();
        for (ClassDateEntity entity : classDateRepository.findByLecture(lecture)) {
            weekdayList.add(entity.getCdWeek());
        }
        LocalDate semesterStart = semester.getSiStartDay();
        LocalDate semesterEnd = semester.getSiEndDay();
        List<LocalDate> semesterDates = new ArrayList<>();
        for (int i=0; i<=ChronoUnit.DAYS.between(semesterStart, semesterEnd); i++) {
            LocalDate date = semesterStart.plusDays(i);
            for (int j=0; j<weekdayList.size(); j++) {
                // 학기 중 휴일은 하드코딩으로 제외
                if (date.getDayOfWeek().getValue()==weekdayList.get(j) && !date.isEqual(LocalDate.of(2023, 3, 1)) && !date.isEqual(LocalDate.of(2023, 5, 5)) && !date.isEqual(LocalDate.of(2023, 6, 6))) {
                    semesterDates.add(date);
                }
            }
        }
        for (int i=0; i<semesterDates.size(); i++) {
            LocalDate date = semesterDates.get(i);
            AttendInfoMasterEntity entity = AttendInfoMasterEntity.builder()
                    .amasDate(date)
                    .amasLiSeq(lecture.getLiSeq())
                    .build();
            System.out.println(entity);
            attendInfoMasterRepository.save(entity);
        }
        for (AttendInfoMasterEntity data : attendInfoMasterRepository.findByLecture(lecture)) {
            for (ClassRegisterEntity data2 : classRegisterRepository.findByLectureInfo(lecture)) {
                AttendInfoStudentEntity entity = AttendInfoStudentEntity.builder()
                        .astuMbSeq(data2.getStudent().getMbSeq())
                        .astuMasSeq(data.getAmasSeq())
                        .build();
                System.out.println(entity);
                attendInfoStudentRepository.save(entity);
            }
        }
    }

    @Test // 강의의 전체 학생출결 데이터 출력
    @Transactional
    void getAttendDay() {
        LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
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
                attStu.setDate(data.getAttendInfoMaster().getAmasDate());
                attStu.setStatus(status);
                attStuList.add(attStu);
            }
            attMas.setMbSeq(stuData.getMbSeq());
            attMas.setName(stuData.getMbName());
            attMas.setList(attStuList);
            resultList.add(attMas);
        }
        System.out.println(resultList);
        // for (ClassRegisterEntity data : classRegisterRepository.findByLectureInfo(lecture)) {
        //     AttendResponseVO attMas = new AttendResponseVO();
        //     attMas.setSeq(data.getStudent().getMbSeq());
        //     attMas.setName(data.getStudent().getMbName());
        //     for (AttendInfoMasterEntity data2 : attendInfoMasterRepository.findByLecture(lecture)) {
        //         List<AttendStuResponseVO> attStuList = new ArrayList<>();
        //         // LocalDate date = null; 
        //         String status = "";
        //         AttendStuResponseVO attStu = new AttendStuResponseVO();
        //         for (AttendInfoStudentEntity data3 : data2.getAstuEntityList()) {
        //             if (data3.getAstuStatus()==null) status = "";
        //             else if (data3.getAstuStatus()==1) status = "O";
        //             else if (data3.getAstuStatus()==0) status = "X";
        //             attStu.setDate(data2.getAmasDate());
        //             attStu.setStatus(status);
        //             attStuList.add(attStu);
        //         }

        //         attMas.setList(attStuList);
        //     }
        //     System.out.println(attMas.toString());
        // }
    }

    // @Test // 출결일 중 전체 학생의 하루의 출석/결석 상태 일괄 변경
    // void patchAllAttendDay() {
    //     Integer status = 1;
    //     String strDate = "2023-02-28";
    //     LocalDate date = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
    //     LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
    //     AttendInfoMasterEntity data = attendInfoMasterRepository.findByLectureAndAmasDate(lecture, date);
    //     AttendResponseVO attMas = new AttendResponseVO();
    //     attMas.setDate(data.getAmasDate().toString());
    //     List<AttendStuResponseVO> attStuList = new ArrayList<>();
    //     for (AttendInfoStudentEntity data2 : attendInfoStudentRepository.findByAttendInfoMaster(data)) {
    //         AttendStuResponseVO attStu = new AttendStuResponseVO();
    //         attStu.setName(data2.getStudent().getMbName());
    //         if (status==1) {
    //             attStu.setStatus("O");
    //         }
    //         else if (status==0) {
    //             attStu.setStatus("X");
    //         }
    //         attStuList.add(attStu);
    //         data2.ChangeStatus(status);
    //         attendInfoStudentRepository.save(data2);
    //     }
    //     attMas.setList(attStuList);
    //     System.out.println(attMas.toString());
    // }

    // @Test // 출결일 중 한 학생의 하루의 출석/결석 상태 변경
    // void patchStuAttendDay() {
    //     Long mbSeq = 1L;
    //     Integer status = 0;
    //     String strDate = "2023-02-28";
    //     LocalDate date = LocalDate.parse(strDate, DateTimeFormatter.ISO_DATE);
    //     MemberBasicEntity student = memberBasicRepository.findByMbSeq(mbSeq);
    //     LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
    //     AttendInfoMasterEntity data = attendInfoMasterRepository.findByLectureAndAmasDate(lecture, date);
    //     AttendResponseVO attMas = new AttendResponseVO();
    //     attMas.setDate(data.getAmasDate().toString());
    //     List<AttendStuResponseVO> attStuList = new ArrayList<>();

    //     AttendInfoStudentEntity data2 = attendInfoStudentRepository.findByAttendInfoMasterAndStudent(data, student);
    //     AttendStuResponseVO attStu = new AttendStuResponseVO();
    //     attStu.setName(data2.getStudent().getMbName());
    //     if (status==1) {
    //         attStu.setStatus("O");
    //     }
    //     else if (status==0) {
    //         attStu.setStatus("X");
    //     }
    //     attStuList.add(attStu);
    //     data2.ChangeStatus(status);
    //     attendInfoStudentRepository.save(data2);

    //     attMas.setList(attStuList);
    //     System.out.println(attMas.toString());
    // }

	@Test // 강의의 과제리스트 조회
	void getAssignmentInfo() {
        LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        ScoreCateEntity scoreCate = scoreCateRepository.findById(4L).orElseThrow(() -> new CustomException("존재하지 않는 평가기준입니다."));
		ScoreStandardEntity scoreStandard = scoreStandardRepository.findByLectureInfoAndScoreCate(lecture, scoreCate);
		
		for (ScoreMasterEntity sMasEntity : scoreMasterRepository.findByScoreStandard(scoreStandard)) {
			System.out.println("--------------------------------------");
			System.out.println("번호 : "+sMasEntity.getSmasSeq());
			System.out.println("이름 : "+sMasEntity.getSmasName());
			System.out.println("평가일 : "+sMasEntity.getSmasDate());
		}
	}

    @Test
    void getTestScore() {
        LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        ScoreCateEntity scoreCate = scoreCateRepository.findById(2L).orElseThrow(() -> new CustomException("존재하지 않는 평가기준입니다."));
        ScoreStandardEntity scoreStan = scoreStandardRepository.findByLectureInfoAndScoreCate(lecture, scoreCate);
        if (scoreStan==null) throw new CustomException("이 강의에서 평가하지 않는 기준입니다.");
        List<ScoreMasterEntity> scoreMasList = scoreMasterRepository.findByScoreStandard(scoreStan);
        ScoreResponseVO response = new ScoreResponseVO();
        List<ScoreMasResponseVO> resultList = new ArrayList<>();
        List<ScoreStuResponseVO> sstuList = new ArrayList<>();
        for (ScoreMasterEntity data : scoreMasList) {
            ScoreMasResponseVO result = new ScoreMasResponseVO();
            for (StudentEntity data2 : studentRepository.findAll()) {
                ScoreStuResponseVO sstuResponse = new ScoreStuResponseVO();
                ScoreStudentEntity scoreStu = scoreStudentRepository.findByScoreMasterAndStudent(data, data2);
                sstuResponse.setSstuSeq(data2.getMbSeq());
                sstuResponse.setName(data2.getMbName());
                sstuResponse.setScore(scoreStu.getSstuScore());
                sstuList.add(sstuResponse);
            }
            result.setSmasSeq(data.getSmasSeq());
            result.setSmasName(data.getSmasName());
            result.setSmasDate(data.getSmasDate());
            result.setSmasScore(data.getSmasScore());
            result.setList(sstuList);
            resultList.add(result);
        }
        response.setList(resultList);
        response.setStatus(true);
        response.setMessage("조회 성공");
        System.out.println(response);
    }

}
