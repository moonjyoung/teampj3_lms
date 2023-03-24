package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.score.ScoreCateEntity;
// import com.greenart.lms_service.entity.score.MaxScoreEntity;
// import com.greenart.lms_service.entity.score.MaxScoreKeyEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.LectureInfoRepository;
// import com.greenart.lms_service.repository.score.MaxScoreRepository;
import com.greenart.lms_service.repository.score.ScoreStandardRepository;
import com.greenart.lms_service.vo.score.MaxScoreAllResponseVO;
import com.greenart.lms_service.vo.score.MaxScoreBasicResponseVO;
import com.greenart.lms_service.vo.score.MaxScoreListResponseVO;
import com.greenart.lms_service.vo.score.MaxScoreResponseVO;
import com.greenart.lms_service.vo.score.UpdateEvaluationTypeVO;
import com.greenart.lms_service.vo.score.UpdateMaxScoreVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final LectureInfoRepository lectureInfoRepository;
    private final ScoreStandardRepository scoreStandardRepository;
    // private final MaxScoreRepository maxScoreRepository;
    
    public MaxScoreResponseVO getLectureScoreMax(Long liSeq) {
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        MaxScoreResponseVO result = new MaxScoreResponseVO();
        List<MaxScoreListResponseVO> scoreList = new ArrayList<>();

        for (ScoreStandardEntity data : scoreStandardRepository.findByLectureInfoOrderByScoreCate(lecture)) {
            MaxScoreListResponseVO vo = new MaxScoreListResponseVO();
            vo.setSsSeq(data.getSsSeq());
            vo.setCateName(data.getScoreCate().getScName());
            vo.setScoreMax(data.getSsScoreMax());
            scoreList.add(vo);
        }

        result.setStatus(true);
        result.setMessage("조회 성공");
        result.setLiSeq(liSeq);
        result.setCode(lecture.getLiCode());
        
        String evaluation = "";
        if (lecture.getLiEvaluationType()==1) evaluation = "상대평가";
        else if (lecture.getLiEvaluationType()==2) evaluation = "절대평가";
        result.setEvaluation(evaluation);

        result.setName(lecture.getLiName());
        result.setList(scoreList);

        return result;
    }

    public MaxScoreBasicResponseVO getLectureScoreMaxAll() {
        List<MaxScoreAllResponseVO> lecList = new ArrayList<>();
        
        for (LectureInfoEntity lecture : lectureInfoRepository.findAll()) {
            MaxScoreAllResponseVO result = new MaxScoreAllResponseVO();
            List<MaxScoreListResponseVO> scoreList = new ArrayList<>();
            
            for (ScoreStandardEntity data : scoreStandardRepository.findByLectureInfoOrderByScoreCate(lecture)) {
                MaxScoreListResponseVO vo = new MaxScoreListResponseVO();
                vo.setSsSeq(data.getSsSeq());
                vo.setCateName(data.getScoreCate().getScName());
                vo.setScoreMax(data.getSsScoreMax());
                scoreList.add(vo);
            }
            
            result.setLiSeq(lecture.getLiSeq());
            result.setCode(lecture.getLiCode());

            String evaluation = "";
            if (lecture.getLiEvaluationType()==1) evaluation = "상대평가";
            else if (lecture.getLiEvaluationType()==2) evaluation = "절대평가";
            result.setEvaluation(evaluation);
    
            result.setName(lecture.getLiName());
            result.setList(scoreList);

            lecList.add(result);
        }

        MaxScoreBasicResponseVO response = new MaxScoreBasicResponseVO();
        response.setStatus(true);
        response.setMessage("전체 조회 성공");
        response.setList(lecList);

        return response;
    }

    // 강의별 평가방식 정보 수정
    public UpdateEvaluationTypeVO updateEvaluationType(Long liSeq, UpdateEvaluationTypeVO data) {
        LectureInfoEntity updateType = lectureInfoRepository.findById(liSeq).orElseThrow();
        updateType.setEvaluationType(data);
        lectureInfoRepository.save(updateType);
        return data;
    }

    // 만점 수정
    public UpdateMaxScoreVO updateMaxScore(LectureInfoEntity lecture,ScoreCateEntity score ,UpdateMaxScoreVO data) {
        ScoreStandardEntity updateMax = scoreStandardRepository.findByLectureInfoAndScoreCate(lecture, score);
        updateMax.setSsScoreMax(data);
        scoreStandardRepository.save(updateMax);
        return data;
    }
    // public UpdateMaxScoreVO updateMaxScore(Long liSeq, Long scSeq, UpdateMaxScoreVO data) {
    //     MaxScoreEntity updateMax = new MaxScoreEntity();
    //     MaxScoreKeyEntity id = new MaxScoreKeyEntity(liSeq, scSeq);
    //     // MaxScoreEntity updateMax = maxScoreRepository.findById(new MaxScoreKeyEntity(liSeq, scSeq));
    //     updateMax.setId(id);
    //     updateMax.setSsScoreMax(data);
    //     maxScoreRepository.save(updateMax);
    //     return data;
    //     // return updateMax.orElseGet(MaxScoreEntity::new);
    // }
}
