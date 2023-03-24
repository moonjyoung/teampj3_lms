package com.greenart.lms_service.entity.score;

import com.greenart.lms_service.entity.LectureInfoEntity;
import com.greenart.lms_service.vo.score.UpdateMaxScoreVO;

import jakarta.persistence.*;
import org.hibernate.annotations.DynamicInsert;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Builder
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@DynamicInsert
@Table(name = "score_standard")
public class ScoreStandardEntity {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ss_seq") private Long ssSeq;
    @Column(name = "ss_score_max") private Integer ssScoreMax;
    @ManyToOne @JoinColumn(name = "ss_sc_seq") private ScoreCateEntity scoreCate;
    @ManyToOne @JoinColumn(name = "ss_li_seq") private LectureInfoEntity lectureInfo;
    @Column(name = "ss_status") private Boolean ssStatus;
    @Column(name = "ss_open_status") private Boolean ssOpenStatus;

    public void setSsScoreMax(UpdateMaxScoreVO data) {
        this.ssScoreMax = data.getMaxScore();
    }
}
