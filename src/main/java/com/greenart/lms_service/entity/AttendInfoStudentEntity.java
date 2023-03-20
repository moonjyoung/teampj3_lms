package com.greenart.lms_service.entity;

import com.greenart.lms_service.entity.member.MemberBasicEntity;
import com.greenart.lms_service.entity.member.StudentEntity;

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
    @Column(name = "astu_seq") private Long astuSeq;
    @Column(name = "astu_status") private Integer astuStatus;
    @Column(name = "astu_mb_seq") private Long astuMbSeq;
    @Column(name = "astu_mas_seq") private Long astuMasSeq;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "astu_mb_seq", insertable = false, updatable = false) private StudentEntity student;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "astu_mas_seq", insertable = false, updatable = false) private AttendInfoMasterEntity attendInfoMaster;

    public void ChangeStatus(Integer status) {
        this.astuStatus = status;
    }
}
