package com.greenart.lms_service.vo.student;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Entity
@Table(name = "lecturestudentcatelistscorevoview")
public class LectureStudentCateListScoreVoVIEW {
    @Id
    @Column(name="smas_seq") private Long smasSeq;
    @Column(name="smas_name") private String smasName;
    @Column(name="sstu_score") private Integer sstuScore;
    @Column(name="smas_score") private Integer smasScore;
    @Column(name="mb_seq") private Long mbSeq;
    @Column(name="li_seq") private Long liSeq;
}
