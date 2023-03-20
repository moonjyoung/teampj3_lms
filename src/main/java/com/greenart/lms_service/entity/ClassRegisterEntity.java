package com.greenart.lms_service.entity;

import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.entity.score.FInalGradeEntity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Table(name = "class_register")
@Entity
public class ClassRegisterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_seq") private Long crSeq;

    @ManyToOne @JoinColumn(name = "cr_li_seq") private LectureInfoEntity lectureInfo;

    @ManyToOne @JoinColumn(name = "cr_mb_seq") private StudentEntity student;

    @OneToOne @JoinColumn(name = "cr_fg_seq") private FInalGradeEntity finalGrade;

}
