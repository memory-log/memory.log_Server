package dgsw.memorylog.memorylog_Server.lib;

import org.springframework.stereotype.Component;

import java.util.Random;

@Component
public class MakeRandomCode {
    public String createCode(int len) {
        Random rand = new Random();
        String code = "";
        for(int i = 0; i < len; i++) {
            String ran = Integer.toString(rand.nextInt(10));
        }
        return code;
    }
}
