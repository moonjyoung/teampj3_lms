package com.greenart.lms_service.entity;

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
@Table(name = "class_date")
@Entity
public class ClassDateEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cd_seq") private Long cdSeq;
    @Column(name = "cd_week") private Integer cdWeek;
    @Column(name = "cd_start") private Integer cdStart;
    @Column(name = "cd_last") private Integer cdLast;
    @ManyToOne @JoinColumn(name = "cd_li_seq") private LectureInfoEntity lecture;
}
