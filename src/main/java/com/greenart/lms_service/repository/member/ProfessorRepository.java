package com.greenart.lms_service.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.member.ProfessorEntity;

public interface ProfessorRepository extends JpaRepository<ProfessorEntity, Long> {
    ProfessorEntity findByMbId(String mbId);
    public ProfessorEntity findByMbIdAndMbPwd(String mbId, String mbPwd);
}
