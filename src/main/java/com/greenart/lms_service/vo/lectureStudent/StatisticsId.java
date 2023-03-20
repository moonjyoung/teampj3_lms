package com.greenart.lms_service.vo.lectureStudent;

import java.io.Serializable;

import jakarta.persistence.Column;

public class StatisticsId implements Serializable {
    @Column(name = "cr_li_seq")
    private Long crLiSeq;
    
    @Column(name = "stu_seq")
    private Long stuSeq;
}