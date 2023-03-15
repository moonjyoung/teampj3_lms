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
@Table(name = "class_register")
@Entity
public class ClassRegisterEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cr_seq") private Long crSeq;
    @Column(name = "cr_li_seq") private Long crLiSeq;
    @Column(name = "cr_stu_seq") private Long crStuSeq;
    @Column(name = "cr_fg_seq") private Long crFgSeq;
}
