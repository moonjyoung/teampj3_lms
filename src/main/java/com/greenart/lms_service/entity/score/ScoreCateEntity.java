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
@Table(name = "score_cate")
public class ScoreCateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sc_seq") private Long scSeq;
    @Column(name = "sc_name") private String scName;
}
