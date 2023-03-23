package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import com.greenart.lms_service.vo.finalGrade.vo.MessageVO;
import com.greenart.lms_service.vo.score.*;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.ScoreCateEntity;
import com.greenart.lms_service.entity.score.ScoreMasterEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;
import com.greenart.lms_service.entity.score.ScoreStudentEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.ClassRegisterRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.StudentRepository;
import com.greenart.lms_service.repository.score.ScoreCateRepository;
import com.greenart.lms_service.repository.score.ScoreMasterRepository;
import com.greenart.lms_service.repository.score.ScoreStandardRepository;
import com.greenart.lms_service.repository.score.ScoreStudentRepository;
import com.greenart.lms_service.vo.BasicResponse;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ScoreService {
    private final StudentRepository studentRepository;
    private final LectureInfoRepository lectureInfoRepository;
    private final ClassRegisterRepository classRegisterRepository;
    private final ScoreCateRepository scoreCateRepository;
    private final ScoreStandardRepository scoreStandardRepository;
    private final ScoreMasterRepository scoreMasterRepository;
    private final ScoreStudentRepository scoreStudentRepository;

    public ScoreResponseVO getTestScore(Long liSeq, Long scSeq) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        ScoreCateEntity scoreCate = scoreCateRepository.findById(scSeq).orElseThrow(() -> new CustomException("존재하지 않는 평가기준입니다."));
        ScoreStandardEntity scoreStan = scoreStandardRepository.findByLectureInfoAndScoreCate(lecture, scoreCate);
        if (scoreStan==null) throw new CustomException("이 강의에서 평가하지 않는 기준입니다.");
        List<StudentEntity> stuList = new ArrayList<>();
        for (ClassRegisterEntity entity : classRegisterRepository.findByLectureInfo(lecture)) {
            stuList.add(entity.getStudent());
        }
        
        List<ScoreMasterEntity> scoreMasList = scoreMasterRepository.findByScoreStandard(scoreStan);
        ScoreResponseVO response = new ScoreResponseVO();
        List<ScoreMasResponseVO> resultList = new ArrayList<>();
        
        for (ScoreMasterEntity data : scoreMasList) {
            List<ScoreStuResponseVO> sstuList = new ArrayList<>();
            ScoreMasResponseVO result = new ScoreMasResponseVO();
            for (StudentEntity data2 : stuList) {
                ScoreStuResponseVO sstuResponse = new ScoreStuResponseVO();
                ScoreStudentEntity scoreStu = scoreStudentRepository.findByScoreMasterAndStudent(data, data2);
                sstuResponse.setSstuSeq(scoreStu.getSstuSeq());
                sstuResponse.setMbSeq(scoreStu.getStudent().getMbSeq());
                sstuResponse.setName(scoreStu.getStudent().getMbName());
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
        return response;
    }

    public MessageVO insertScore(Long liSeq, ScoreInsertVO data) {
        ScoreMasterEntity scoreMaster = scoreMasterRepository.findById(data.getMasSeq()).orElseThrow(() -> new NullPointerException("존재하지 않는 평가 번호"));
        StudentEntity student = studentRepository.findById(data.getMbSeq()).orElseThrow(() -> new NullPointerException("존재하지 않는 학생 번호"));
        LectureInfoEntity lectureInfo = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new NullPointerException("존재하지 않는 강의 번호"));
        if(scoreMaster.getScoreStandard().getLectureInfo() != lectureInfo) {
            return MessageVO.builder()
                    .status(false)
                    .message("해당 강의와 맞지 않는 평가 번호입니다.")
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        if(data.getScore() > scoreMaster.getSmasScore()) {
            return MessageVO.builder()
                    .status(false)
                    .message("평가 점수는 최댓값을 넘을 수 없습니다. "+scoreMaster.getSmasName()+"의 최댓값 : "+scoreMaster.getSmasScore())
                    .code(HttpStatus.BAD_REQUEST)
                    .build();
        }
        ScoreStudentEntity score = new ScoreStudentEntity(null, scoreMaster, student, data.getScore());
        scoreStudentRepository.save(score);
        return MessageVO.builder()
                .status(true)
                .message(student.getMbName()+"( "+student.getMbId()+" ) 학생의 "+scoreMaster.getSmasName()+" 점수 : "+data.getScore())
                .code(HttpStatus.OK)
                .build();
    }
}
