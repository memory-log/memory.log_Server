package dgsw.memorylog.memorylog_Server.service.Member;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.repository.MemberRepository;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignInVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignUpVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepo;

    /**
     * 로그인
     * @return 멤버 인덱스
     */
    @Override
    public Integer signIn(MemberSignInVo memberSignInVo) {
        try {
            Member member = memberRepo.findByEmailAndPw(memberSignInVo.getEmail(), memberSignInVo.getPw());

            if (member == null) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "로그인 실패");
            }

            return member.getIdx();
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Integer signUp(MemberSignUpVo memberSignUpVo) {
        try {
            ModelMapper modelMapper = new ModelMapper();
            Member mappedMember = modelMapper.map(memberSignUpVo, Member.class);
            Member createdMember = memberRepo.save(mappedMember);
            return createdMember.getIdx();
        } catch (Exception e) {
            throw e;
        }
    }

    /**
     * 이메일 중복 확인
     * @param email
     * @return
     */
    @Override
    public boolean validateDupEmail(String email) {
        Member emailDuplicateMember = memberRepo.findByEmail(email);
        if (emailDuplicateMember != null) {
            throw new HttpClientErrorException(HttpStatus.CONFLICT, "중복된 이메일.");
        }

        return true;
    }
}
