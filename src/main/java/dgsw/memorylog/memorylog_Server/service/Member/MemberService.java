package dgsw.memorylog.memorylog_Server.service.Member;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberEditVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignInVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignUpVo;

public interface MemberService {
    public Integer signIn(MemberSignInVo memberSignInVo);
    public Integer signUp(MemberSignUpVo memberSignUpVo);
    public void validateDupEmail(String email);
    public void editInfo(MemberEditVo memberEditVo, Member member);
    public Member getOtherInfo(Integer idx);
}
