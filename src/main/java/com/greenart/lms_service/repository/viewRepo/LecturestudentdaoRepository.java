package com.greenart.lms_service.repository.viewRepo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.web.bind.annotation.PathVariable;

import com.greenart.lms_service.vo.lectureStudent.LectureStudentDAO;


public interface LecturestudentdaoRepository extends JpaRepository<LectureStudentDAO, Long>{
    // 내 강의 학생 조회
    List<LectureStudentDAO> findByProSeqAndLiSeq(Long proSeq, Long liSeq);
    // 내 강의 학생 검색
    List<LectureStudentDAO> findByLiSeqAndStuNameContains(Long liSeq, String stuName);
    List<LectureStudentDAO> findByLiSeqAndMbSeq(Long liSeq, Long mbSeq);
}
