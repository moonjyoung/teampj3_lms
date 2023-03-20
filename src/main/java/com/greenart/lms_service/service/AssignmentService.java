package com.greenart.lms_service.service;



import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.score.ScoreCateEntity;
import com.greenart.lms_service.entity.score.ScoreMasterEntity;
import com.greenart.lms_service.entity.score.ScoreStandardEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.score.ScoreCateRepository;
import com.greenart.lms_service.repository.score.ScoreMasterRepository;
import com.greenart.lms_service.repository.score.ScoreStandardRepository;
import com.greenart.lms_service.vo.AssignmentListResponseVO;
import com.greenart.lms_service.vo.AssignmentResponseVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class AssignmentService {
    private final ScoreStandardRepository scoreStandardRepository;
    private final LectureInfoRepository lectureInfoRepository;
    private final ScoreMasterRepository scoreMasterRepository;
    private final ScoreCateRepository scoreCateRepository;

    public AssignmentResponseVO liAList(Long liSeq) { // 일단 liSeq를 담을거여요
        LectureInfoEntity lecture = lectureInfoRepository.findById(liSeq).orElseThrow(() -> new CustomException("강의번호를 확인해주세요.")); // 내가 담은 LiSeq를 LectureInfoRepository를 통해서 찾아서 Entity의 lecture에 담는다
        ScoreCateEntity scoreCate = scoreCateRepository.findById(4L).orElseThrow(() -> new CustomException("번호를 확인해주세요."));
        ScoreStandardEntity score = scoreStandardRepository.findByLectureInfoAndScoreCate(lecture, scoreCate); // liSeq를 담은 lecture를 ScoreStandardRepository로 찾아서 score에 담고
                
        AssignmentResponseVO assi = new AssignmentResponseVO();
        List<AssignmentListResponseVO> resultList = new ArrayList<>();
        for(ScoreMasterEntity data : scoreMasterRepository.findByScoreStandard(score)){
            AssignmentListResponseVO assiList = new AssignmentListResponseVO();
            assiList.setASeq(data.getSMasSeq());
            assiList.setAName(data.getSMasName());
            assiList.setADate(data.getSMasDate().toString());
            resultList.add(assiList);
        }
        assi.setStatus(true);
        assi.setMessage("조회 성공");
        assi.setList(resultList);
        return assi;
    }
       
        



        



        
        
        
    }
    

