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
@Table(name = "score_standard")
public class ScoreStandardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ss_seq") private Long ssSeq;
    @Column(name = "ss_score_max") private Integer ssScoreMax;
    @Column(name = "ss_sc_seq") private Long ssScSeq;
    @Column(name = "ss_li_seq") private Long ssLiSeq;
    @Column(name = "ss_status") private Boolean ssStatus;
    @Column(name = "ss_open_status") private Boolean ssOpenStatus;
}
