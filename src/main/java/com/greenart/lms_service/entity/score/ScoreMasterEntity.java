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
    @Column(name = "s_mas_seq") private Long sMasSeq;
    @Column(name = "s_mas_ss_seq") private Long sMasSsSeq;
    @Column(name = "s_mas_name") private String sMasName;
    @Column(name = "s_mas_date") private LocalDate sMasDate;
    @Column(name = "s_mas_score") private Integer sMasScore;
}
