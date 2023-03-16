package com.greenart.lms_service.finalscore.VO;

import lombok.*;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalScoreVO {
    private String studentName;
    private String studentCode;
    private List<ScoreInfoVO> scoreList;
    private Integer maxScore;
    private Integer totalScore;
    private Integer rank;
    private String grade;
}
