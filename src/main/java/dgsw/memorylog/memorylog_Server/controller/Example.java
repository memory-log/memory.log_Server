package dgsw.memorylog.memorylog_Server.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;

@CrossOrigin
@RestController
@RequestMapping("/")
public class Example {
    @GetMapping("/post")
    @ResponseBody()
    public Object helloWorld() {
        ObjectMapper mapper = new ObjectMapper();
        HashMap<String, String> map = new HashMap<String, String>();
        map.put("status", "200");
        try {
            return mapper.writeValueAsString(map);
        }catch (Exception e){
            return 0;
        }
    }
}
