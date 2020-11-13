package dgsw.memorylog.memorylog_Server.interceptor;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.lib.AuthorizationExtractor;
import dgsw.memorylog.memorylog_Server.service.jwt.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;
import org.thymeleaf.util.StringUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Component
public class AuthInterceptor implements HandlerInterceptor {

    @Autowired
    private AuthorizationExtractor authExtractor;

    @Autowired
    private JwtServiceImpl jwtService;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) {
        try {
            String token = authExtractor.extract(request, "Bearer");

            if (!StringUtils.isEmpty(token)) {
                Member member = jwtService.validateToken(token);
                request.setAttribute("member", member);
            } else {
                if (request.getMethod().equals("OPTIONS")) {
                    return true;
                }
                throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "인증 안됨.");
            }


            return true;
        } catch (HttpClientErrorException e) {
            throw e;
        }
    }
}
