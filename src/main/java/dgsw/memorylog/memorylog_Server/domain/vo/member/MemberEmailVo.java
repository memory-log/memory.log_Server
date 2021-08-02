package dgsw.memorylog.memorylog_Server.domain.vo.member;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
public class MemberEmailVo {
    @Email
    @NotBlank
    private String email;
}
