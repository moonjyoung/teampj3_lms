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
import com.greenart.lms_service.service.AttendService;
import com.greenart.lms_service.vo.attend.AttendMasResponseVO;
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

    @Autowired private AttendService attendService;

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
        AttendResponseVO result = new AttendResponseVO();
        List<AttendMasResponseVO> attMasList = new ArrayList<>();
        for (StudentEntity stuData : stuList) {
            AttendMasResponseVO attMas = new AttendMasResponseVO();
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
            attMas.setMbSeq(stuData.getMbSeq());
            attMas.setName(stuData.getMbName());
            attMas.setList(attStuList);
            attMasList.add(attMas);
        }
        result.setStatus(true);
        result.setMessage("조회 성공");
        result.setList(attMasList);
        System.out.println(result);
    }

    @Test // 출결일 중 전체 학생의 하루의 출석/결석 상태 일괄 변경
    @Transactional
    void patchAllAttendDay() {
        LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        AttendInfoMasterEntity attendMas = attendInfoMasterRepository.findById(1L).orElseThrow(() -> new CustomException("강의일 번호를 확인해주세요."));
        if (attendMas.getLecture()!=lecture) throw new CustomException("강의 정보와 강의일 정보가 맞지 않습니다.");
        for (AttendInfoStudentEntity attendStu : attendInfoStudentRepository.findByAttendInfoMaster(attendMas)) {
            attendStu.ChangeStatus(1);
            attendInfoStudentRepository.save(attendStu);
        }
    }

    @Test // 출결일 중 한 학생의 하루의 출석/결석 상태 변경
    void patchStuAttendDay() {
        LectureInfoEntity lecture = lectureInfoRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        StudentEntity student = studentRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 학생입니다."));

        ClassRegisterEntity classRegi = classRegisterRepository.findByLectureInfoAndStudent(lecture, student);
        if (classRegi==null) throw new CustomException("강의에 학생이 없습니다.");

        AttendInfoMasterEntity attendMas = attendInfoMasterRepository.findById(1L).orElseThrow(() -> new CustomException("존재하지 않는 강의일 입니다."));
        AttendInfoStudentEntity attendStu = attendInfoStudentRepository.findByAttendInfoMasterAndStudent(attendMas, student);
        if (attendStu==null) throw new CustomException("올바르지 않은 입력입니다.");

        attendStu.ChangeStatus(1);
        attendInfoStudentRepository.save(attendStu);
    }

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

    @Test
    void putAttFGrade() { // 출결 F 적용 여부 테스트 코드
        attendService.putAttendFGrade("BAC001-01");
    }

}
