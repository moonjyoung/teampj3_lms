package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.ClassRegisterEntity;

public interface ClassRegisterRepository extends JpaRepository<ClassRegisterEntity, Long> {
    public List<ClassRegisterEntity> findByCrLiSeq(Long crLiSeq);
}
