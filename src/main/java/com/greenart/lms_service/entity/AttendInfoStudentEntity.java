package com.greenart.lms_service.entity;

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
@DynamicInsert
@Table(name = "attend_info_student")
@Entity
public class AttendInfoStudentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ai_stu_seq") private Long aiStuSeq;
    @Column(name = "ai_stu_status") private Integer aiStuStatus;
    @Column(name = "ai_stu_stu_seq") private Long aiStuStuSeq;
    @Column(name = "ai_stu_mas_seq") private Long aiStuMasSeq;
}
