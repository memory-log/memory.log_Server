package dgsw.memorylog.memorylog_Server.controller;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.vo.http.Response;
import dgsw.memorylog.memorylog_Server.domain.vo.http.ResponseData;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberEmailVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignInVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberSignUpVo;
import dgsw.memorylog.memorylog_Server.domain.vo.member.MemberTokenVo;
import dgsw.memorylog.memorylog_Server.enums.JwtToken;
import dgsw.memorylog.memorylog_Server.service.EmailAuthentication.EmailAuthenticationServiceImpl;
import dgsw.memorylog.memorylog_Server.service.Member.MemberServiceImpl;
import dgsw.memorylog.memorylog_Server.service.jwt.JwtServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@CrossOrigin
@Api(value = "멤버 API")
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    @Autowired
    private JwtServiceImpl jwtService;

    @Autowired
    private EmailAuthenticationServiceImpl emailAuthenticationService;

    @GetMapping("/getinfo")
    @ApiOperation(value = "내 정보 받기")
    public ResponseData getinfo(HttpServletRequest request) {
        try {
            Member member = (Member)request.getAttribute("member");
            Map<String, Object> data = new HashMap<String, Object>();
            System.out.println("멤버" + member);
            data.put("Idx", member.getIdx());
            data.put("name", member.getName());
            data.put("Email", member.getEmail());
            return new ResponseData(HttpStatus.OK, "내 정보 받기 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @PostMapping("/signin")
    @ApiOperation(value = "로그인")
    public ResponseData signin(@RequestBody @Valid MemberSignInVo memberSignInVo) {
        try {
            Integer memberIdx = memberService.signIn(memberSignInVo);
            String accessToken = jwtService.createToken(memberIdx, JwtToken.ACCESS);
            String refreshToken = jwtService.createToken(memberIdx, JwtToken.REFRESH);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("accessToken", accessToken);
            data.put("refreshToken", refreshToken);
           return new ResponseData(HttpStatus.OK, "로그인 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @PostMapping("/signup")
    @ApiOperation(value = "회원가입")
    public ResponseData signup(@RequestBody @Valid MemberSignUpVo memberSignUpVo) {
        try {
            Integer memberIdx = memberService.signUp(memberSignUpVo);
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("member_idx", memberIdx);
            return new ResponseData(HttpStatus.OK, "가입 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @PostMapping("/token")
    @ApiOperation(value = "토큰 갱신")
    public ResponseData token(@RequestBody @Valid MemberTokenVo memberTokenVo) {
        try {
            String accessToken = jwtService.refresh(memberTokenVo.getRefreshToken());
            Map<String, Object> data = new HashMap<String, Object>();
            data.put("accessToken", accessToken);
            return new ResponseData(HttpStatus.OK, "갱신 성공.", data);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    @PostMapping("/email/code")
    @ApiOperation(value = "인증 메일 발송")
    public Response sendEmailCode(@RequestBody @Valid MemberEmailVo memberEmailVo) {
        try {
            memberService.validateDupEmail(memberEmailVo.getEmail());
            emailAuthenticationService.sendEmailCode(memberEmailVo.getEmail());
            return new Response(HttpStatus.OK, "전송 성공.");
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
