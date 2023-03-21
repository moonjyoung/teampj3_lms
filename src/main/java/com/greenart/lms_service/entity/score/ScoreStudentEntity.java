package com.greenart.lms_service.entity.score;

import com.greenart.lms_service.entity.member.MemberBasicEntity;
import com.greenart.lms_service.entity.member.StudentEntity;

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
@Entity
@DynamicInsert
@Table(name = "score_student")
public class ScoreStudentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sstu_seq") private Long sstuSeq;
    @ManyToOne @JoinColumn(name = "sstu_mas_seq") private ScoreMasterEntity scoreMaster;
    @ManyToOne @JoinColumn(name = "sstu_mb_seq") private StudentEntity student;
    @Column(name = "sstu_score") private Integer sstuScore;

    public void ChangeScore(Integer score) {
        this.sstuScore = score;
    }
}
