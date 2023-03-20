package com.greenart.lms_service.entity;

import com.greenart.lms_service.entity.member.MemberBasicEntity;
import com.greenart.lms_service.entity.member.ProfessorEntity;
import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "lecture_info")
@Entity
public class LectureInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "li_seq") private Long liSeq;
    @Column(name = "li_code") private String liCode;
    @Column(name = "li_name") private String liName;
    @Column(name = "li_class") private String liClass;
    @Column(name = "li_grade") private Integer liGrade;
    @Column(name = "li_evaluation_type") private Integer liEvaluation_type;
    @Column(name = "li_content") private String liContent;
    @ManyToOne @JoinColumn(name = "li_mb_seq") private ProfessorEntity professor;
    // @Column(name = "li_si_seq") private Long liSiSeq;
    @OneToOne @JoinColumn(name = "li_si_seq") SemesterInfoEntity semesterInfoEntity;
}
