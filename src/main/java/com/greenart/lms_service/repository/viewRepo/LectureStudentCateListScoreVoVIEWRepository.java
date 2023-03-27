package com.greenart.lms_service.repository.viewRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.vo.student.LectureStudentCateListScoreVoVIEW;

public interface LectureStudentCateListScoreVoVIEWRepository extends JpaRepository<LectureStudentCateListScoreVoVIEW, Long>{
    List<LectureStudentCateListScoreVoVIEW> findByLiSeqAndMbSeq(Long liSeq, Long mbSeq);
}
