package com.greenart.lms_service;

import java.time.LocalDate;
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


	@Test
	void putAttendDay(Long liSeq) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        SemesterInfoEntity semester = lecture.getSemesterInfoEntity();
        List<Integer> weekdayList = new ArrayList<>();
        for (ClassDateEntity entity : classDateRepository.findByCdLiSeq(liSeq)) {
            weekdayList.add(entity.getCdWeek());
        }
        LocalDate semesterStart = semester.getSiStartDay();
        LocalDate semesterEnd = semester.getSiEndDay();
        List<LocalDate> semesterDates = new ArrayList<>();
        for (int i=0; i<=ChronoUnit.DAYS.between(semesterStart, semesterEnd); i++) {
            LocalDate date = semesterStart.plusDays(i);
            for (int j=0; j<weekdayList.size(); j++) {
                if (date.getDayOfWeek().getValue()==weekdayList.get(j) && !date.isEqual(LocalDate.of(2023, 3, 1)) && !date.isEqual(LocalDate.of(2023, 5, 5)) && !date.isEqual(LocalDate.of(2023, 6, 6))) {
                    semesterDates.add(date);
                }
            }
        }
        for (int i=0; i<semesterDates.size(); i++) {
            LocalDate date = semesterDates.get(i);
            AttendInfoMasterEntity entity = AttendInfoMasterEntity.builder()
                    .aiMasDate(date)
                    .aiMasLiSeq(liSeq)
                    .build();
            attendInfoMasterRepository.save(entity);
        }
        for (AttendInfoMasterEntity data : attendInfoMasterRepository.findByAiMasLiSeq(liSeq)) {
            for (ClassRegisterEntity data2 : classRegisterRepository.findByCrLiSeq(liSeq)) {
                AttendInfoStudentEntity entity = AttendInfoStudentEntity.builder()
                        .aiStuStuSeq(data2.getCrStuSeq())
                        .aiStuMasSeq(data.getAiMasSeq())
                        .build();
                attendInfoStudentRepository.save(entity);
            }
        }
    }

	@Test
	void getAssignmentInfo() {
		ScoreStandardEntity scoreStandard = scoreStandardRepository.findBySsLiSeqAndSsScSeq(1L, 4L);
		System.out.println(scoreStandard.getSsSeq());
		List<ScoreMasterEntity> scoreMasterList = scoreMasterRepository.findBySmasSsSeq(scoreStandard.getSsSeq());
		for (ScoreMasterEntity sMasEntity : scoreMasterRepository.findBySmasSsSeq(scoreStandard.getSsSeq())) {
			System.out.println("--------------------------------------");
			System.out.println("번호 : "+sMasEntity.getSmasSeq());
			System.out.println("이름 : "+sMasEntity.getSmasName());
			System.out.println("평가일 : "+sMasEntity.getSmasDate());
		}
	}

}
