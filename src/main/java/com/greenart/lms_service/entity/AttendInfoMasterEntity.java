package com.greenart.lms_service.entity;

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
@DynamicInsert
@Table(name = "attend_info_master")
@Entity
public class AttendInfoMasterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amas_seq") private Long aMasSeq;
    @Column(name = "amas_date") private LocalDate aMasDate;
    @ManyToOne
    @JoinColumn(name = "amas_li_seq") private LectureInfoEntity lecture;
}
