package com.greenart.lms_service.vo.finalGrade.view;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ViewRepository extends JpaRepository<ViewEntity, Long> {
    List<ViewEntity> findByLectureAndStudent(String lecture, String student);
}
