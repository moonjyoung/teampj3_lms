package com.greenart.lms_service.entity.member;

import org.hibernate.annotations.DynamicInsert;

import jakarta.persistence.Column;
import jakarta.persistence.DiscriminatorColumn;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

// @Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "member_basic")
@Inheritance(strategy = InheritanceType.JOINED)
@DiscriminatorColumn(name = "mb_type")
public abstract class MemberBasicEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mb_seq") private Long mbSeq;
    @Column(name = "mb_id") private String mbId;
    @Column(name = "mb_pwd") private String mbPwd;
    @Column(name = "mb_name") private String mbName;
    @Column(name = "mb_email") private String mbEmail;
    // @Column(name = "mb_type", insertable=false, updatable=false) private String mbType;

    public MemberBasicEntity(String mbId, String mbPwd, String mbName, String mbEmail) {
        this.mbId = mbId;
        this.mbPwd = mbPwd;
        this.mbName = mbName;
        this.mbEmail = mbEmail;
    }
}
