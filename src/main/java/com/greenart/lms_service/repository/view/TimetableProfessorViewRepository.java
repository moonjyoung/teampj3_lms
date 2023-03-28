package com.greenart.lms_service.repository.view;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.view.TimetableProfessorView;

public interface TimetableProfessorViewRepository extends JpaRepository<TimetableProfessorView, Long> {
    List<TimetableProfessorView> findByMbSeqAndSiSeq(Long mbSeq, Long siSeq);
}
