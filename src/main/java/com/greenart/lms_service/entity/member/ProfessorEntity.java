package com.greenart.lms_service.entity.member;

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
@Table(name = "professor")
@DiscriminatorValue("P")
public class ProfessorEntity extends MemberBasicEntity {
    @Column(name = "prof_subject") private String profSubject;

    @Builder
    public ProfessorEntity(String mbId, String mbPwd, String mbName, String mbEmail, String profSubject) {
        super(mbId, mbPwd, mbName, mbEmail);
        this.profSubject = profSubject;
    }
}
