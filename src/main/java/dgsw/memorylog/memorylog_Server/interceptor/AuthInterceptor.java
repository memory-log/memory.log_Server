package dgsw.memorylog.memorylog_Server.interceptor;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.lib.AuthorizationExtractor;
import dgsw.memorylog.memorylog_Server.service.jwt.JwtServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.servlet.HandlerInterceptor;

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
        String token = authExtractor.extract(request, "Bearer");
        if (StringUtils.isEmpty(token)) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "권한 없음.");
        }

        Member member = jwtService.validateToken(token);

        request.setAttribute("member", member);

        return true;
    }
}
