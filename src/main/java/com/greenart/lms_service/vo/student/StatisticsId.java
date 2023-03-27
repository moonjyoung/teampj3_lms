package com.greenart.lms_service.vo.student;

import java.io.Serializable;

import jakarta.persistence.Column;

public class StatisticsId implements Serializable {
    @Column(name = "li_seq")
    private Long liSeq;
    
    @Column(name = "mb_seq")
    private Long mbSeq;
}