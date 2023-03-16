package com.greenart.lms_service.entity.score;

import java.time.LocalDate;

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
@Table(name = "score_master")
public class ScoreMasterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smas_seq") private Long smasSeq;
    @Column(name = "smas_ss_seq") private Long smasSsSeq;
    @Column(name = "smas_name") private String smasName;
    @Column(name = "smas_date") private LocalDate smasDate;
    @Column(name = "smas_score") private Integer smasScore;
}
