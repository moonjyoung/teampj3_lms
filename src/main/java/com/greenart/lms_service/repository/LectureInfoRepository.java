package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.ProfessorEntity;

public interface LectureInfoRepository extends JpaRepository<LectureInfoEntity, Long> {
    LectureInfoEntity findByLiCode(String liCode);
    LectureInfoEntity findByLiSeq(Long liSeq);
   // List<LectureInfoEntity> findByLiSeq (Long liSeq);

    
    List<LectureInfoEntity> findByProfessor(ProfessorEntity professor);
}
