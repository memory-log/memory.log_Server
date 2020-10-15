package dgsw.memorylog.memorylog_Server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dgsw.memorylog.memorylog_Server.database.vo.member.MemberLoginVo;
import dgsw.memorylog.memorylog_Server.lib.MakeJson;
import dgsw.memorylog.memorylog_Server.service.Member.MemberServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/member")
public class MemberController {

    @Autowired
    private MemberServiceImpl memberService;

    MakeJson makeJson = new MakeJson();

    @GetMapping("/signin")
    @ResponseBody()
    public Object login(@RequestBody @Valid MemberLoginVo memberLoginVo) {
        try {
            Integer memberIdx = memberService.login(memberLoginVo);
            makeJson.setJson("Status", 200);
            makeJson.setJson("Idx", memberIdx);
            return makeJson.getJson();
        }catch (Exception e){
            makeJson.setJson("Status", 400);
            makeJson.setJson("Idx", "존재하지 않는 회원");
            return makeJson.getJson();
        }
    }

}
