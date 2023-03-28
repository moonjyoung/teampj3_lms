package com.greenart.lms_service.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.view.TimetableStudentView;

public interface TimetableStudentViewRepository extends JpaRepository<TimetableStudentView, Long> {
    List<TimetableStudentView> findByMbSeqAndSiSeq(Long mbSeq, Long siSeq);
}
