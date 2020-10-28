package dgsw.memorylog.memorylog_Server.domain.vo.member;

import lombok.Getter;

import javax.validation.constraints.NotBlank;

@Getter
public class MemberTokenVo {
    @NotBlank
    private String refreshToken;
}
