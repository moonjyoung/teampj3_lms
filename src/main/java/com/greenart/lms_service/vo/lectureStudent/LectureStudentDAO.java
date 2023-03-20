package com.greenart.lms_service.vo.lectureStudent;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "lecturestudentdao")
public class LectureStudentDAO {
    @Column(name = "pro_seq") private Long proSeq;
    @Column(name = "pro") private String proName;
    @Column(name = "li_name") private String liName;
    @Column(name = "li_mb_seq") private Long liMbSeq;
    @Column(name = "li_code") private String liCode;
    @Column(name = "cr_li_seq") private Long crLiSeq;
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY) // 유니크 키가 필요합니다. 예로 정해주기 원래는 따로 있어야함
    @Column(name = "stu_seq") private Long stuSeq;
    @Column(name = "stu") private String stuName;
    @Column(name = "mb_id") private String mbId;
    @Column(name = "stu_subject") private String stuSubject;
    @Column(name = "stu_grade") private Integer stuGrade;
}
