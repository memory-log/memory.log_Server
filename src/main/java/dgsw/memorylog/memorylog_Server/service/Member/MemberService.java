package dgsw.memorylog.memorylog_Server.service.Member;

import dgsw.memorylog.memorylog_Server.database.vo.member.MemberLoginVo;

public interface MemberService {
    public Integer login(MemberLoginVo memberLoginVo);
}
