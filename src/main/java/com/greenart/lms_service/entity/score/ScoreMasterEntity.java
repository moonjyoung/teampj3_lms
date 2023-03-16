package com.greenart.lms_service.entity.score;

import java.time.LocalDate;

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
@Table(name = "score_master")
public class ScoreMasterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "smas_seq") private Long smasSeq;
    @ManyToOne @JoinColumn(name = "smas_ss_seq") private ScoreStandardEntity scoreStandard;
    @Column(name = "smas_name") private String smasName;
    @Column(name = "smas_date") private LocalDate smasDate;
    @Column(name = "smas_score") private Integer smasScore;
}
