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
@Table(name = "staff")
@DiscriminatorValue("U")
public class StaffEntity extends MemberBasicEntity {
    @Column(name = "staff_work") private String staffWork;

    @Builder
    public StaffEntity(String mbId, String mbPwd, String mbName, String mbEmail, String staffWork) {
        super(mbId, mbPwd, mbName, mbEmail);
        this.staffWork = staffWork;
    }
}
