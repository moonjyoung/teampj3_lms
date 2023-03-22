package com.greenart.lms_service.vo.member;

import com.greenart.lms_service.entity.member.ProfessorEntity;
import com.greenart.lms_service.entity.member.StaffEntity;
import com.greenart.lms_service.entity.member.StudentEntity;
import com.greenart.lms_service.security.vo.TokenVO;
import com.greenart.lms_service.vo.BasicResponse;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class MemberLoginResponseVO extends BasicResponse {
    @Schema(description = "회원 번호", example = "1")
    private Long seq;
    @Schema(description = "회원 아이디", example = "2023123001")
    private String id;
    @Schema(description = "회원 이름", example = "강백호")
    private String name;
    @Schema(description = "회원 타입", example = "student")
    private String type;
    @Schema(description = "토큰")
    private TokenVO token;

    public MemberLoginResponseVO(StudentEntity student) {
        this.seq = student.getMbSeq();
        this.id = student.getMbId();
        this.name = student.getMbName();
        this.type = "student";
    }
    public MemberLoginResponseVO(ProfessorEntity professor) {
        this.seq = professor.getMbSeq();
        this.id = professor.getMbId();
        this.name = professor.getMbName();
        this.type = "professor";
    }
    public MemberLoginResponseVO(StaffEntity staff) {
        this.seq = staff.getMbSeq();
        this.id = staff.getMbId();
        this.name = staff.getMbName();
        this.type = "staff";
    }
    public MemberLoginResponseVO() {}
}
