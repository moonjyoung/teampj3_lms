package com.greenart.lms_service.vo.finalGrade.view;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicInsert;

@Data
@AllArgsConstructor
@NoArgsConstructor
@DynamicInsert
@Entity
@Table(name = "final_score")
public class ViewEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "seq") private Long seq;
    @Column(name = "lecture") private String lecture;
    @Column(name = "student") private String student;
    @Column(name = "name") private String name;
    @Column(name = "total_max_score") private Integer totalMaxScore;
    @Column(name = "explanation") private String explanation;
    @Column(name = "max_score") private Integer maxScore;
    @Column(name = "score") private Integer score;
}
