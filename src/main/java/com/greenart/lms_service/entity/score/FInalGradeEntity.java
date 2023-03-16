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
@Table(name = "final_grade")
public class FInalGradeEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "fg_seq") private Long fgSeq;
    @Column(name = "fg_name") private String fgName;
    @Column(name = "fg_point3") private Double fgPoint3;
    @Column(name = "fg_point5") private Double fgPoint5;
}
