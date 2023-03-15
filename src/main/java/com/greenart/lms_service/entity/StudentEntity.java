package com.greenart.lms_service.entity;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "student")
@DiscriminatorValue("S")
public class StudentEntity extends MemberBasicEntity {
    @Column(name = "stu_subject") private String stuSubject;
    @Column(name = "stu_grade") private Integer stuGrade;

    @Builder
    public StudentEntity(String mbId, String mbPwd, String mbName, String mbEmail, String stuSubject, Integer stuGrade) {
        super(mbId, mbPwd, mbName, mbEmail);
        this.stuSubject = stuSubject;
        this.stuGrade = stuGrade;
    }
}
