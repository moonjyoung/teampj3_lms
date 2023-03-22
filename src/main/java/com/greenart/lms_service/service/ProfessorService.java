package com.greenart.lms_service.service;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.ClassDateEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.ProfessorEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.ClassDateRepository;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.member.ProfessorRepository;
import com.greenart.lms_service.utils.ConvertDayOfWeek;
import com.greenart.lms_service.vo.lecture.LectureResponseVO;
import com.greenart.lms_service.vo.lecture.ResponseLectureListVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProfessorService {
    private final ProfessorRepository professorRepository;
    private final LectureInfoRepository lectureInfoRepository;
    private final ClassDateRepository classDateRepository;

    public LectureResponseVO getMyLecture(Long mbSeq) {
        ProfessorEntity professor =  professorRepository.findById(mbSeq).orElseThrow(() -> new CustomException("교수정보가 존재하지 않습니다."));
        LectureResponseVO result = new LectureResponseVO();
        List<ResponseLectureListVO> listVO = new ArrayList<>();
        for (LectureInfoEntity data : lectureInfoRepository.findByProfessor(professor)) {
            ResponseLectureListVO vo = new ResponseLectureListVO();
            vo.setLiSeq(data.getLiSeq());
            vo.setLiCode(data.getLiCode());
            vo.setLiName(data.getLiName());
            vo.setLiClass(data.getLiClass());
            vo.setLiGrade(data.getLiGrade());
            listVO.add(vo);
        }
        result.setStatus(true);
        result.setMessage("조회 성공");
        result.setList(listVO);

        return result;
    }
}
