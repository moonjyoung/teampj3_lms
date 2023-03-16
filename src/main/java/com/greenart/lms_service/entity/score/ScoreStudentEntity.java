package com.greenart.lms_service.entity.score;

import org.hibernate.annotations.DynamicInsert;

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

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "score_student")
public class ScoreStudentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "s_stu_seq") private Long sStuSeq;
    @Column(name = "s_stu_mas_seq") private Long sStuMasSeq;
    @Column(name = "s_stu_stu_seq") private Long sStuStuSeq;
    @Column(name = "s_stu_score") private Double sStuScore;
}
