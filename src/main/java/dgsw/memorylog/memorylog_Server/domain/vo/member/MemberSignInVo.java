package dgsw.memorylog.memorylog_Server.domain.vo.member;

import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

/**
 * 멤버 로그인 오브젝트
 */
@Getter
public class MemberSignInVo {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String pw;
}
