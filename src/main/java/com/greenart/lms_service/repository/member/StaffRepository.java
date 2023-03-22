package com.greenart.lms_service.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.member.StaffEntity;

public interface StaffRepository extends JpaRepository<StaffEntity, Long> {
    StaffEntity findByMbId(String mbId);
    public StaffEntity findByMbIdAndMbPwd(String mbId, String mbPwd);
}
