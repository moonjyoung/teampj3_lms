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
@Entity
@Table(name = "attend_info_master")
public class AttendInfoMasterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "amas_seq") private Long amasSeq;
    @Column(name = "amas_date") private LocalDate amasDate;
    @Column(name = "amas_li_seq") private Long amasLiSeq;
    @ManyToOne @JoinColumn(name = "amas_li_seq", insertable = false, updatable = false) private LectureInfoEntity lecture;

    public AttendInfoMasterEntity(LocalDate amasDate, Long amasLiSeq) {
        this.amasDate = amasDate;
        this.amasLiSeq = amasLiSeq;
    }
}
