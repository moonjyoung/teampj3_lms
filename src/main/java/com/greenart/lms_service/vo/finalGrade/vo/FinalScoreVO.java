package com.greenart.lms_service.vo.finalGrade.vo;

import com.greenart.lms_service.vo.finalGrade.view.ViewEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FinalScoreVO {
    private String studentName;
    private String studentCode;
    private List<ViewEntity> scoreList;
    private Integer totalMaxScore;
    private Integer totalScore;
    private Integer rank;
    private String grade;
}
