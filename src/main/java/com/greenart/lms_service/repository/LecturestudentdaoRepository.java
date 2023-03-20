package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.vo.lectureStudent.LectureStudentDAO;

public interface LecturestudentdaoRepository extends JpaRepository<LectureStudentDAO, Long>{
    List<LectureStudentDAO> findByProSeqAndCrLiSeq(Long proSeq, Long crLiSeq);


}
