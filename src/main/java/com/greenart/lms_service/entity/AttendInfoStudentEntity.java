package com.greenart.lms_service.entity;

import com.greenart.lms_service.entity.member.MemberBasicEntity;
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
@Table(name = "attend_info_student")
@Entity
public class AttendInfoStudentEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "astu_seq") private Long aiStuSeq;
    @Column(name = "astu_status") private Integer aiStuStatus;
    @ManyToOne
    @JoinColumn(name = "astu_mb_seq") private MemberBasicEntity student;
    @ManyToOne @JoinColumn(name = "astu_mas_seq") private AttendInfoMasterEntity attendInfoMaster;
}
