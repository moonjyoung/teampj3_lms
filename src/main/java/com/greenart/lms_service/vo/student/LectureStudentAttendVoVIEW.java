package com.greenart.lms_service.vo.student;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.IdClass;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@IdClass(StatisticsId.class) // 유니크키 두개 클래스 하나 더 만듬
@Entity
@Table(name = "lecturestudentattendvoview")
public class LectureStudentAttendVoVIEW implements Serializable{
    //LectureStudentAttendVO 를 view로 만듬
        // 강의에서
        //  - 상위카테고리번호 카테고리이름(출석), 출석날짜, 전체날짜

        // private Long scoreCateSeq;
        // private String scoreCateName;
        @Id
        @Column(name = "li_seq") private Long liSeq;
        @Id
        @Column(name = "mb_seq") private Long mbSeq;
        @Column(name = "student_attend") private Integer attendCount;
        @Column(name = "total_attend") private Integer attendCountTotal;
}
