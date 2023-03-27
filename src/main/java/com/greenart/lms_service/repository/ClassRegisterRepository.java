package com.greenart.lms_service.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.greenart.lms_service.entity.ClassRegisterEntity;
import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.entity.member.StudentEntity;

public interface ClassRegisterRepository extends JpaRepository<ClassRegisterEntity, Long> {
    List<ClassRegisterEntity> findByLectureInfo(LectureInfoEntity entity);
    ClassRegisterEntity findByLectureInfoAndStudent(LectureInfoEntity lectureInfo, StudentEntity student);
    List<ClassRegisterEntity> findByStudent(StudentEntity student);

    @Query(value = "select cr.cr_seq, cr.cr_mb_seq , fg.fg_seq as cr_fg_seq, fg.fg_name, cr.cr_li_seq from class_register cr join member_basic mb on mb.mb_seq = cr.cr_mb_seq left join final_grade fg on fg.fg_seq  = cr.cr_fg_seq where mb.mb_seq =:mbSeq and cr.cr_li_seq =:liSeq", nativeQuery=true)
    ClassRegisterEntity findByfinalName(@Param("liSeq") Long liSeq, @Param("mbSeq")Long mbSeq);
}
