package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.score.ScoreStandardRepository;
import com.greenart.lms_service.vo.score.MaxScoreAllResponseVO;
import com.greenart.lms_service.vo.score.MaxScoreBasicResponseVO;
import com.greenart.lms_service.vo.score.MaxScoreListResponseVO;
import com.greenart.lms_service.vo.score.MaxScoreResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StaffService {
    private final LectureInfoRepository lectureInfoRepository;
    private final ScoreStandardRepository scoreStandardRepository;
    
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
        if (lecture.getLiEvaluation_type()==1) evaluation = "상대평가";
        else if (lecture.getLiEvaluation_type()==2) evaluation = "절대평가";
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
            if (lecture.getLiEvaluation_type()==1) evaluation = "상대평가";
            else if (lecture.getLiEvaluation_type()==2) evaluation = "절대평가";
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
}
