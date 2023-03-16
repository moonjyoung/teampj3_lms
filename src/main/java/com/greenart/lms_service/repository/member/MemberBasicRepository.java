package com.greenart.lms_service.repository.member;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.member.MemberBasicEntity;

public interface MemberBasicRepository<T extends MemberBasicEntity> extends JpaRepository<T, Long> {
    MemberBasicEntity findByMbSeq(Long mbSeq);
    
}
