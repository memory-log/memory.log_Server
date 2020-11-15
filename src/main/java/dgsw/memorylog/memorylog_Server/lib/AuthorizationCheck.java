package dgsw.memorylog.memorylog_Server.lib;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;

import javax.servlet.http.HttpServletRequest;

@Component
public class AuthorizationCheck {
    public boolean check(HttpServletRequest request) {
        if (request.getAttribute("member") == null) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "인증 안됨.");
        }
        return true;
    }
}
