package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;

public interface ClassRegisterRepository extends JpaRepository<ClassRegisterEntity, Long> {
    List<ClassRegisterEntity> findByLectureInfo(LectureInfoEntity entity);
    List<ClassRegisterEntity> findByLectureInfoAndStudent(LectureInfoEntity lectureInfo, StudentEntity student);

}
