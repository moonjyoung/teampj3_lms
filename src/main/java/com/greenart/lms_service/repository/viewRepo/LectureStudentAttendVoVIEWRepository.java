package com.greenart.lms_service.repository.viewRepo;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.vo.student.LectureStudentAttendVoVIEW;

public interface LectureStudentAttendVoVIEWRepository extends JpaRepository<LectureStudentAttendVoVIEW, Long>{
    List<LectureStudentAttendVoVIEW> findByLiSeqAndMbSeq(Long liSeq, Long mbSeq );
}
