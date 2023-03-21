package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

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
import com.greenart.lms_service.vo.score.RequestScoreVO;
import com.greenart.lms_service.vo.score.ScoreMasResponseVO;
import com.greenart.lms_service.vo.score.ScoreResponseVO;
import com.greenart.lms_service.vo.score.ScoreStuResponseVO;

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

    public BasicResponse postScore(Long liSeq, RequestScoreVO data) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        ScoreStudentEntity scoreStu = scoreStudentRepository.findById(data.getSstuSeq()).orElseThrow(() -> new CustomException("존재하지 않는 평가입니다."));
        if (scoreStu.getScoreMaster().getScoreStandard().getLectureInfo()!=lecture) throw new CustomException("올바르지 않은 입력입니다.");

        scoreStu.ChangeScore(data.getScore());
        scoreStudentRepository.save(scoreStu);

        return new BasicResponse(true, "입력 완료");
    }
}
