package dgsw.memorylog.memorylog_Server.service.Member;

import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignInVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignUpVo;

public interface MemberService {
    public Integer signIn(MemberSignInVo memberSignInVo);
    public Integer signUp(MemberSignUpVo memberSignUpVo);
}
