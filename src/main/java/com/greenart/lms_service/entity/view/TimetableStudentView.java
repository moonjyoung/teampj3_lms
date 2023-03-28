package com.greenart.lms_service.entity.view;

import java.time.LocalDate;

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

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "timetable_student_view")
public class TimetableStudentView {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "astu_seq") private Long astuSeq;
    @Column(name = "mb_seq") private Long mbSeq;
    @Column(name = "si_seq") private Long siSeq;
    @Column(name = "li_name") private String liName;
    @Column(name = "li_code") private String liCode;
    @Column(name = "amas_date") private LocalDate amasDate;
    @Column(name = "cd_start") private Integer cdStart;
    @Column(name = "cd_last") private Integer cdLast;
}
