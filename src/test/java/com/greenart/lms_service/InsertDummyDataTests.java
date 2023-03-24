package com.greenart.lms_service;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.AttendInfoStudentEntity;
import com.greenart.lms_service.entity.ClassDateEntity;
import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.SemesterInfoEntity;
import com.greenart.lms_service.entity.member.ProfessorEntity;
import com.greenart.lms_service.entity.member.StaffEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.ScoreMasterEntity;
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

import jakarta.transaction.Transactional;

@SpringBootTest
class InsertDummyDataTests {
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
        LectureInfoEntity lecture = lectureInfoRepository.findById(6L).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
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

    @Test // 신규 회원정보 추가
    void putMember() {
        String mbId = "23321020";
        String mbPwd = "1234";
        String mbName = "채소연";
        String mbEmail = "staff020@univ.ac.kr";
        String mbType = "U";

        String stuSubject = "컴퓨터공학";
        Integer stuGrade = 1;

        String profSubject = "컴퓨터공학";

        String staffWork = "교무행정";

        if (mbType.equals("S")) {
            StudentEntity entity = StudentEntity.builder()
                    .mbId(mbId)
                    .mbPwd(mbPwd)
                    .mbName(mbName)
                    .mbEmail(mbEmail)
                    .stuSubject(stuSubject)
                    .stuGrade(stuGrade)
                    .build();
            memberBasicRepository.save(entity);
        }
        else if (mbType.equals("P")) {
            ProfessorEntity entity = ProfessorEntity.builder()
                    .mbId(mbId)
                    .mbPwd(mbPwd)
                    .mbName(mbName)
                    .mbEmail(mbEmail)
                    .profSubject(profSubject)
                    .build();
            memberBasicRepository.save(entity);
        }
        else if (mbType.equals("U")) {
            StaffEntity entity = StaffEntity.builder()
                    .mbId(mbId)
                    .mbPwd(mbPwd)
                    .mbName(mbName)
                    .mbEmail(mbEmail)
                    .staffWork(staffWork)
                    .build();
            memberBasicRepository.save(entity);
        }
    }

    @Test // 시험, 과제, 출석 점수 입력
    void putScore() {
        for (ScoreMasterEntity smas : scoreMasterRepository.findAll()) {
            // ScoreMasterEntity smas = scoreMasterRepository.findById(2L).orElseThrow(() -> new CustomException("존재하지 않는 평가기준입니다."));
            LectureInfoEntity lecture = smas.getScoreStandard().getLectureInfo();
            List<StudentEntity> stuList = new ArrayList<>();
            for (ClassRegisterEntity data : classRegisterRepository.findByLectureInfo(lecture)) {
                stuList.add(data.getStudent());
            }
            Random rand = new Random();
            Integer scoreMax = smas.getSmasScore();
    
            for (StudentEntity data : stuList) {
                ScoreStudentEntity sstu = ScoreStudentEntity.builder()
                        .scoreMaster(smas)
                        .student(data)
                        .sstuScore(rand.nextInt(scoreMax+1))
                        .build();
                scoreStudentRepository.save(sstu);
            }
        }
    }
}
