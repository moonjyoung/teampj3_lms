package com.greenart.lms_service.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.exception.CustomException;
import com.greenart.lms_service.repository.LectureInfoRepository;
import com.greenart.lms_service.repository.LecturestudentdaoRepository;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentDAO;
import com.greenart.lms_service.vo.lectureStudent.LectureStudentDaoVO;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class LectureStudentService {
    private final LecturestudentdaoRepository lecDaoRepo;
    private final LectureInfoRepository lectureInfoRepository;

    // 교수번호(P타입-repo)- (수강신청)강의entity-학생mb_entity(S타입)-stu_entity(전공명) 실패
    // view로 만들어서_view를 담는 vo를 만들어서 필요한 정보만 api명세서 대로 만듬
    // seq, 학번 , 이름, 학년, 전공명
    public List<LectureStudentDaoVO> getLectureStudentList(Long crLiSeq) {
        LectureInfoEntity entity = lectureInfoRepository.findById(crLiSeq).orElseThrow(() -> new CustomException("존재하지 않는 강의입니다."));
        Long proSeq = entity.getProfessor().getMbSeq();
        List<LectureStudentDAO> dao = lecDaoRepo.findByProSeqAndCrLiSeq(proSeq, crLiSeq);
        List<LectureStudentDaoVO> daoVO = new ArrayList<>();

        for(int i=0; i<dao.size(); i++) {
            LectureStudentDaoVO vo = LectureStudentDaoVO.builder()
                // .proName(dao.get(i).getProName())
                // .liName(dao.get(i).getLiName())
                // .liCode(dao.get(i).getLiCode())
                .seq(dao.get(i).getStuSeq())
                .stuName(dao.get(i).getStuName())
                .stuId(dao.get(i).getMbId())
                .stuSubject(dao.get(i).getStuSubject())
                .stuGrade(dao.get(i).getStuGrade())
                .build();
            // vo.setStatus(true);
            // vo.setMessage("성공");
            daoVO.add(vo);
        }
        return daoVO;
    }

}
