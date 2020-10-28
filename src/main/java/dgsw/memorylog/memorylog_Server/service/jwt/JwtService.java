package dgsw.memorylog.memorylog_Server.service.jwt;


import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.enums.JwtToken;

public interface JwtService {
    public String createToken(Integer idx, JwtToken tokenType);
    public Member validateToken(String token);
    public String refresh(String refreshToken);
}