package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenart.lms_service.entity.AttendInfoMasterEntity;
import com.greenart.lms_service.entity.AttendInfoStudentEntity;
import com.greenart.lms_service.entity.member.StudentEntity;


public interface AttendInfoStudentRepository extends JpaRepository<AttendInfoStudentEntity, Long> {
    public List<AttendInfoStudentEntity> findByAttendInfoMaster(AttendInfoMasterEntity attendInfoMaster);
    public List<AttendInfoStudentEntity> findByStudent(StudentEntity student);
    public AttendInfoStudentEntity findByAttendInfoMasterAndStudent(AttendInfoMasterEntity attendInfoMaster, StudentEntity student);
    
}
