package dgsw.memorylog.memorylog_Server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import dgsw.memorylog.memorylog_Server.database.vo.member.MemberLoginVo;
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
    @GetMapping("/login")
    @ResponseBody()
    public Object login(@RequestBody @Valid MemberLoginVo memberLoginVo) {
        try {
            ObjectMapper mapper = new ObjectMapper();
            HashMap<String, String> map = new HashMap<String, String>();
            map.put("사용자의 이메일", memberLoginVo.getEmail());
            map.put("사용자의 패스워드", memberLoginVo.getPw());
            return mapper.writeValueAsString(map);
        }catch (Exception e){
            return 0;
        }
    }
}
