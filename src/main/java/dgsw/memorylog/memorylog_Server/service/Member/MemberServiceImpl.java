package dgsw.memorylog.memorylog_Server.service.Member;

import dgsw.memorylog.memorylog_Server.database.entity.Member;
import dgsw.memorylog.memorylog_Server.database.repository.MemberRepository;
import dgsw.memorylog.memorylog_Server.database.vo.member.MemberLoginVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepo;

    /**
     * 로그인
     * @return 멤버 인덱스
     */
    @ResponseStatus(HttpStatus.OK)
    @Override
    public Integer login(MemberLoginVo memberLoginVo) {
        try {
            Member member = memberRepo.findByEmailAndPw(memberLoginVo.getEmail(), memberLoginVo.getPw());

            if (member == null) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "로그인 실패");
            }

            return member.getIdx();
        } catch (Exception e) {
            throw e;
        }
    }
}
