package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.FinalGradeEntity;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.score.FinalGradeRepository;
import com.greenart.lms_service.repository.score.ScoreStandardRepository;
import com.greenart.lms_service.repository.score.ScoreStudentRepository;
import com.greenart.lms_service.vo.finalGrade.view.RankScore;
import com.greenart.lms_service.vo.finalGrade.view.TotalMaxScore;
import com.greenart.lms_service.vo.finalGrade.view.TotalScore;
import com.greenart.lms_service.vo.finalGrade.view.ViewEntity;
import com.greenart.lms_service.vo.finalGrade.view.ViewRepository;
import com.greenart.lms_service.vo.finalGrade.vo.FinalScoreVO;
import com.greenart.lms_service.vo.finalGrade.vo.InsertFinalScoreVO;
import com.greenart.lms_service.vo.finalGrade.vo.MessageVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class FinalScoreService {
    private final LectureInfoRepository lectureInfoRepository;
    private final ClassRegisterRepository classRegisterRepository;
    private final ScoreStandardRepository scoreStandardRepository;
    private final ScoreStudentRepository scoreStudentRepository;
    private final StudentRepository studentRepository;
    private final FinalGradeRepository finalGradeRepository;
    private final ViewRepository viewRepository;

    public List<FinalScoreVO> getFinalScore(String code) {
        LectureInfoEntity lecture = lectureInfoRepository.findByLiCode(code);
        List<ClassRegisterEntity> studentInClass = classRegisterRepository.findByLectureInfo(lecture);
        List<FinalScoreVO> finalScore = new ArrayList<>();
        TotalMaxScore totalMaxScore = scoreStandardRepository.findByLectureInfo(lecture.getLiSeq());
        List<RankScore> rank = scoreStudentRepository.findByLiSeq(lecture.getLiSeq());
        String grade = "미평가";
        Integer ranking = null;
        for(int i=0; i<studentInClass.size(); i++) {
            List<ViewEntity> view = viewRepository.findByLectureAndStudent(lecture.getLiCode(), studentInClass.get(i).getStudent().getMbId());
            TotalScore totalScore = scoreStudentRepository.findByMbSeq(studentInClass.get(i).getStudent().getMbSeq(), lecture.getLiSeq());
            ClassRegisterEntity getGrade = classRegisterRepository.findByLectureInfoAndStudent(lecture, studentInClass.get(i).getStudent());
            if(getGrade.getFinalGrade() != null) {
                grade = getGrade.getFinalGrade().getFgName();
            }
            for(int j=0; j<rank.size(); j++) {
                if(rank.get(j).getStudentCode().equals(studentInClass.get(i).getStudent().getMbId())) {
                    ranking = rank.get(j).getStudentRank();
                }
            }
            FinalScoreVO response = FinalScoreVO.builder()
                    .studentName(studentInClass.get(i).getStudent().getMbName())
                    .studentCode(studentInClass.get(i).getStudent().getMbId())
                    .scoreList(view)
                    .totalMaxScore(totalMaxScore.getTotalMaxScore())
                    .totalScore(totalScore.getTotalScore())
                    .rank(ranking)
                    .grade(grade)
                    .build();
            finalScore.add(response);
            grade = "미평가";
        }
        return finalScore;
    }

    public MessageVO insertFinalScore(InsertFinalScoreVO data, String lectureCode) {
        LectureInfoEntity lectureInfo = lectureInfoRepository.findByLiCode(lectureCode);
        if(lectureInfo == null) {
            return MessageVO.builder()
                    .status(false)
                    .message("강의 코드를 다시 확인하세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        StudentEntity student = studentRepository.findByMbId(data.getStudentCode());
        if(student == null) {
            return MessageVO.builder()
                    .status(false)
                    .message("학번을 다시 확인하세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }

        ClassRegisterEntity finalGrade = classRegisterRepository.findByLectureInfoAndStudent(lectureInfo, student);
        FinalGradeEntity checkGrade = finalGradeRepository.findByFgSeq(data.getGrade());
        if(checkGrade == null) {
            return MessageVO.builder()
                    .status(false)
                    .message("성적값을 다시 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        finalGrade.setFinalGrade(checkGrade);
        classRegisterRepository.save(finalGrade);
        return MessageVO.builder()
                .status(true)
                .message(student.getMbName()+"("+student.getMbId()+") 학생의 최종 학점 : "+checkGrade.getFgName())
                .code(HttpStatus.OK)
                .build();
    }

    public MessageVO updateFinalScore(InsertFinalScoreVO data, String lectureCode) {
        LectureInfoEntity lectureInfo = lectureInfoRepository.findByLiCode(lectureCode);
        if(lectureInfo == null) {
            return MessageVO.builder()
                    .status(false)
                    .message("강의 코드를 다시 확인하세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        StudentEntity student = studentRepository.findByMbId(data.getStudentCode());
        if(student == null) {
            return MessageVO.builder()
                    .status(false)
                    .message("학번을 다시 확인하세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        ClassRegisterEntity finalGrade = classRegisterRepository.findByLectureInfoAndStudent(lectureInfo, student);
        String beforeUpdate = finalGrade.getFinalGrade().getFgName();
        FinalGradeEntity checkGrade = finalGradeRepository.findByFgSeq(data.getGrade());
        if(data.getGrade() == 0) {
            finalGrade.setFinalGrade(null);
            classRegisterRepository.save(finalGrade);
            return MessageVO.builder()
                    .status(true)
                    .message(student.getMbName()+"("+student.getMbId()+") 학생의 최종 학점 부여를 취소했습니다.")
                    .code(HttpStatus.OK)
                    .build();
        }
        if(checkGrade == null) {
            return MessageVO.builder()
                    .status(false)
                    .message("성적값을 다시 확인해주세요.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        finalGrade.setFinalGrade(checkGrade);
        classRegisterRepository.save(finalGrade);
        return MessageVO.builder()
                .status(true)
                .message(student.getMbName()+"("+student.getMbId()+") 학생의 최종 학점 : "+checkGrade.getFgName()+" (변경 전 학점 : "+beforeUpdate+")")
                .code(HttpStatus.OK)
                .build();
    }
}
