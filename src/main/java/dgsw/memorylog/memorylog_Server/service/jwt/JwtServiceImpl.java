package dgsw.memorylog.memorylog_Server.service.jwt;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.repository.MemberRepository;
import dgsw.memorylog.memorylog_Server.enums.JwtToken;
import dgsw.memorylog.memorylog_Server.lib.DateConstant;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Service
public class JwtServiceImpl implements JwtService {
    @Autowired
    private MemberRepository memberRepo;

    @Value("${jwt.secret.access}")
    private String secretAccessKey;

    @Value("${jwt.secret.refresh}")
    private String secretRefreshKey;

    private SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;

    /**
     * 토큰 생성
     * @param idx
     * @param tokenType
     * @return Token
     */
    @Override
    public String createToken(Integer idx, JwtToken tokenType) {
        Date expiredAt = new Date();
        String secretKey = "";

        switch (tokenType) {
            case REFRESH:
                expiredAt.setTime(expiredAt.getTime() + DateConstant.MILLISECONDS_FOR_A_HOUR);
                secretKey = secretAccessKey;
                break;
            case ACCESS:
                expiredAt.setTime(expiredAt.getTime() + DateConstant.MILLISECONDS_FOR_A_HOUR * 24 * 7);
                secretKey = secretRefreshKey;
                break;
        }

        Key signInKey = new SecretKeySpec(secretKey.getBytes(), signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> claimsMap = new HashMap<String, Object>();

        claimsMap.put("idx", idx);

        JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap)
                .setClaims(claimsMap)
                .setExpiration(expiredAt)
                .signWith(SignatureAlgorithm.HS256, signInKey);

        return builder.compact();
    }


    /**
     * 토큰 검증
     * @param token
     * @return Member
     */
    @Override
    public Member validateToken(String token) {
        try {
            Key signInKey = new SecretKeySpec(secretAccessKey.getBytes(), signatureAlgorithm.getJcaName());
            Claims claims = Jwts.parser().setSigningKey(signInKey)
                    .parseClaimsJws(token).getBody();

            Optional<Member> member = memberRepo.findById((Integer) claims.get("idx"));

            if (!member.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "회원 없음");
           }

            return member.get();
        } catch (ExpiredJwtException e) {
            throw new HttpClientErrorException(HttpStatus.GONE, "토큰 만료.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조.");
        } catch (IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }

    /**
     * 토큰 갱신
     * @param refreshToken
     * @return accessToken
     */
    @Override
    public String refresh(String refreshToken) {
        try {
            Key signInKey = new SecretKeySpec(secretRefreshKey.getBytes(), signatureAlgorithm.getJcaName());
            Claims claims = Jwts.parser().setSigningKey(signInKey)
                    .parseClaimsJws(refreshToken).getBody();

            Optional<Member> member = memberRepo.findById((Integer) claims.get("idx"));

            if (!member.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "회원 없음");
            }

            return createToken(member.get().getIdx(), JwtToken.ACCESS);
        } catch (ExpiredJwtException e) {
            throw new HttpClientErrorException(HttpStatus.GONE, "토큰 만료.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조.");
        } catch (IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음.");
        } catch (Exception e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
