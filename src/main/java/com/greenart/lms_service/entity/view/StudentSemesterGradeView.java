package com.greenart.lms_service.entity.view;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "student_semester_grade_view")
public class StudentSemesterGradeView {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_seq") private Long crSeq;
    @Column(name = "mb_seq") private Long mbSeq;
    @Column(name = "mb_name") private String mbName;
    @Column(name = "mb_id") private String mbId;
    @Column(name = "stu_subject") private String stuSubject;
    @Column(name = "stu_grade") private Integer stuGrade;
    @Column(name = "li_code") private String liCode;
    @Column(name = "li_name") private String liName;
    @Column(name = "li_grade") private Integer liGrade;
    @Column(name = "li_si_seq") private Long liSiSeq;
    @Column(name = "fg_name") private String fgName;
    @Column(name = "fg_point3") private Double fgPoint3;
    @Column(name = "fg_point5") private Double fgPoint5;
}
