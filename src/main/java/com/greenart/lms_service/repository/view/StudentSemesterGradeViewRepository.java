package com.greenart.lms_service.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.view.StudentSemesterGradeView;

public interface StudentSemesterGradeViewRepository extends JpaRepository<StudentSemesterGradeView, Long> {
    List<StudentSemesterGradeView> findByMbSeqAndLiSiSeq(Long mbSeq, Long liSiSeq);
}
