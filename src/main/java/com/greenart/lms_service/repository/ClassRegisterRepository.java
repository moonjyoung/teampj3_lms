package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.EntityGraph;
import org.springframework.data.jpa.repository.JpaRepository;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;

public interface ClassRegisterRepository extends JpaRepository<ClassRegisterEntity, Long> {
    // @Query(value = "select cr_li_seq from class_register", nativeQuery = true)
    // ClassRegisterEntity findByCrLiSeq(Long crLiSeq);

    List<ClassRegisterEntity> findByLectureInfo(LectureInfoEntity entity);

    List<ClassRegisterEntity> findByLectureInfoAndStudent(LectureInfoEntity lectureInfo, StudentEntity student);

    // @Query(value = "SELECT cr.cr_li_seq, mb.mb_id , mb.mb_name ,s.stu_subject , s.stu_grade"+
    //     "FROM class_register cr"+
    //     "JOIN member_basic mb on cr.cr_mb_seq = mb.mb_seq"+
    //     "JOIN student s on s.mb_seq = mb.mb_seq", nativeQuery = true
    //     )
    // List<ClassRegisterEntity> findByClassRegister();

    @EntityGraph( attributePaths = {"LectureInfoEntity","StudentEntity"})
    List<ClassRegisterEntity> findByClassRegister(ClassRegisterEntity entity);

}
