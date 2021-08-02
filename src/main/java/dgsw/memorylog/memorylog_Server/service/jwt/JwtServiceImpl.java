package dgsw.memorylog.memorylog_Server.service.jwt;

import dgsw.memorylog.memorylog_Server.domain.entity.Member;
import dgsw.memorylog.memorylog_Server.domain.repository.MemberRepository;
import dgsw.memorylog.memorylog_Server.enums.JwtToken;
import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

import javax.crypto.spec.SecretKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.Key;
import java.util.*;

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
        Date now = new Date();
        Calendar expiredAt = Calendar.getInstance();
        expiredAt.setTime(now);

        String secretKey = "";

        switch (tokenType) {
            case ACCESS:
                expiredAt.add(Calendar.HOUR, 1);
                secretKey = secretAccessKey;
                break;
            case REFRESH:
                expiredAt.add(Calendar.DATE, 30);
                secretKey = secretRefreshKey;
                break;
        }

        byte[] apiKeySecretBytes = DatatypeConverter.parseBase64Binary(secretKey);
        Key signingKey = new SecretKeySpec(apiKeySecretBytes, signatureAlgorithm.getJcaName());

        Map<String, Object> headerMap = new HashMap<String, Object>();

        headerMap.put("typ", "JWT");
        headerMap.put("alg", "HS256");

        Map<String, Object> claimsMap = new HashMap<String, Object>();

        claimsMap.put("idx", idx);

        JwtBuilder builder = Jwts.builder().setHeaderParams(headerMap)
                .setClaims(claimsMap)
                .setExpiration(expiredAt.getTime())
                .signWith(signatureAlgorithm, signingKey);

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
            if (StringUtils.isEmpty(token)) {
                throw new HttpClientErrorException(HttpStatus.FORBIDDEN, "권한 없음.");
            }

            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretAccessKey))
                    .parseClaimsJws(token).getBody();

            Optional<Member> member = memberRepo.findById((Integer) claims.get("idx"));

            if (!member.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "회원 없음");
            }

            return member.get();
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (ExpiredJwtException e) {
            throw new HttpClientErrorException(HttpStatus.GONE, "토큰 만료.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조.");
        } catch (IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음.");
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
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
            Claims claims = Jwts.parser().setSigningKey(DatatypeConverter.parseBase64Binary(secretRefreshKey))
                    .parseClaimsJws(refreshToken).getBody();

            Optional<Member> member = memberRepo.findById((Integer) claims.get("idx"));

            if (!member.isPresent()) {
                throw new HttpClientErrorException(HttpStatus.NOT_FOUND, "회원 없음");
            }

            return createToken(member.get().getIdx(), JwtToken.ACCESS);
        } catch (HttpClientErrorException e) {
            throw e;
        } catch (ExpiredJwtException e) {
            e.printStackTrace();
            throw new HttpClientErrorException(HttpStatus.GONE, "토큰 만료.");
        } catch (SignatureException | MalformedJwtException e) {
            throw new HttpClientErrorException(HttpStatus.UNAUTHORIZED, "토큰 위조.");
        } catch (IllegalArgumentException e) {
            throw new HttpClientErrorException(HttpStatus.BAD_REQUEST, "토큰 없음.");
        } catch (Exception e) {
            throw new HttpServerErrorException(HttpStatus.INTERNAL_SERVER_ERROR, "서버 오류.");
        }
    }
}
