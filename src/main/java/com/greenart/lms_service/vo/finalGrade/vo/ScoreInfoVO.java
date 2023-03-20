package com.greenart.lms_service.vo.finalGrade.vo;

import lombok.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScoreInfoVO {
    private String name;
    private Integer totalMaxScore;
    private String explanation;
    private Integer maxScore;
    private Integer score;

}
