package com.greenart.lms_service.entity;

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
@Table(name = "semester_detail_info")
public class SemesterDetailInfoEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "sdi_seq") private Long sdiSeq;
    @Column(name = "sdi_week") private Integer sdiWeek;
    @Column(name = "sdi_start") private LocalDate sdiStart;
    @Column(name = "sdi_end") private LocalDate sdiEnd;
    @Column(name = "sdi_si_seq") private Long sdiSiSeq;
    @Column(name = "sdi_memo") private String sdiMemo;
}
