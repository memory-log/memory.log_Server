package dgsw.memorylog.memorylog_Server.database.vo.member;

import com.sun.istack.NotNull;
import lombok.Getter;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;

/**
 * 멤버 로그인 오브젝트
 */
@Getter
public class MemberLoginVo {
    @Email
    @NotBlank
    private String email;

    @NotBlank
    private String pw;
}
