package dgsw.memorylog.memorylog_Server.service.Member;

import dgsw.memorylog.memorylog_Server.domain.entity.EmailAuthentication;
import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.repository.EmailAuthenticationRepository;
import dgsw.memorylog.memorylog_Server.domain.repository.MemberRepository;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberEditVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignInVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignUpVo;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.thymeleaf.util.StringUtils;

import java.util.Optional;

@Service
public class MemberServiceImpl implements MemberService{
    @Autowired
    private MemberRepository memberRepo;

    @Autowired
    private EmailAuthenticationRepository EmailAuthenticationRepo;

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

    /**
     * 회원가입
     * @param memberSignUpVo
     * @return 멤버 인덱스
     */
    @Override
    public Integer signUp(MemberSignUpVo memberSignUpVo) {
        try {
            validateDupEmail(memberSignUpVo.getEmail());
            EmailAuthentication emailAuthentication = EmailAuthenticationRepo.findByEmail(memberSignUpVo.getEmail());

            if (emailAuthentication == null || !emailAuthentication.getIsCertified()) {
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "인증 안됨.");
            }

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
     */
    @Override
    public void validateDupEmail(String email) {
        try {
            Member emailDuplicateMember = memberRepo.findByEmail(email);

            if (emailDuplicateMember != null) {
                throw new HttpClientErrorException(HttpStatus.CONFLICT, "중복된 이메일.");
            }
        } catch (Exception e) {
            throw e;
        }
    }


    /**
     * 내 정보 수정
     * @param memberEditVo
     * @param member
     */
    @Override
    public void editInfo(MemberEditVo memberEditVo, Member member) {
        try {
            if (member == null) {
                throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "권한 없음.");
            }
            boolean nameIsExist = StringUtils.isEmpty(memberEditVo.getName());
            boolean profileImageIsExist = StringUtils.isEmpty(memberEditVo.getProfileImage());

            if (nameIsExist && profileImageIsExist) {
                throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "검증 오류.");
            }

            member.setName(Optional.ofNullable(memberEditVo.getName()).orElse(member.getName()));
            member.setProfileImage(Optional.ofNullable(memberEditVo.getProfileImage()).orElse(member.getProfileImage()));

            memberRepo.save(member);
        } catch (Exception e) {
            throw e;
        }
    }

    @Override
    public Member getOtherInfo(Integer idx) {
        try {
            Optional<Member> member = memberRepo.findById(idx);

            if (!member.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "회원 없음");
            }

            return member.get();
        } catch (Exception e) {
            throw e;
        }
    }
}
