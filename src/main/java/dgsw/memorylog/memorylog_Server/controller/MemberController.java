package dgsw.memorylog.memorylog_Server.controller;

import dgsw.memorylog.memorylog_Server.database.vo.member.MemberSignInVo;
import dgsw.memorylog.memorylog_Server.database.vo.member.MemberSignUpVo;
import dgsw.memorylog.memorylog_Server.lib.MakeJson;
import dgsw.memorylog.memorylog_Server.service.Member.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    MakeJson makeJson = new MakeJson();

    @GetMapping("/signin")
    @ResponseBody()
    public Object signin(@RequestBody @Valid MemberSignInVo memberSignInVo) {
        try {
            Integer memberIdx = memberService.signIn(memberSignInVo);
            makeJson.setJson("Status", 200);
            makeJson.setJson("Idx", memberIdx);
            makeJson.setJson("Message", "로그인 성공");
            return makeJson.getJson();
        }catch (Exception e){
            makeJson.setJson("Status", 400);
            makeJson.setJson("Idx", null);
            makeJson.setJson("Message", "로그인 실패");
            return makeJson.getJson();
        }
    }

    @GetMapping("/signup")
    @ResponseBody()
    public Object signup(@RequestBody @Valid MemberSignUpVo memberSignUpVo) {
        try {
            Integer memberIdx = memberService.signUp(memberSignUpVo);
            makeJson.setJson("Status", 200);
            makeJson.setJson("Idx", memberIdx);
            makeJson.setJson("Message", "회원가입 성공");
            return makeJson.getJson();
        }catch (Exception e) {
            makeJson.setJson("Status", 500);
            makeJson.setJson("Idx", null);
            makeJson.setJson("Message", "회원가입 실패");
            return makeJson.getJson();
        }
    }

}
