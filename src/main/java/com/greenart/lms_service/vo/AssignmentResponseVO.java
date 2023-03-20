package com.greenart.lms_service.vo;


import java.time.LocalDate;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class AssignmentResponseVO extends BasicResponse{
    private List<AssignmentListResponseVO> list;
}
