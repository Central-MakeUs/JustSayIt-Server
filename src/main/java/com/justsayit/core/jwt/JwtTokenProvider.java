package com.justsayit.core.jwt;

import com.justsayit.core.jwt.dto.JwtToken;
import io.jsonwebtoken.*;
import io.jsonwebtoken.security.Keys;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import javax.servlet.ServletRequest;
import java.util.Arrays;
import java.util.Collection;
import java.util.Date;
import java.util.stream.Collectors;

@Slf4j
@Component
public class JwtTokenProvider {

    @Value("${jwt.secret.key}")
    private String SECRET_KEY;

    @Value("${jwt.secret.expiration-time}")
    private int ACCESS_TOKEN_EXPRIATION_TIME;

    public JwtToken createToken(Long memberId) {
        String accessToken = createAccessToken(memberId);
        return new JwtToken(accessToken);
    }

    public String createAccessToken(Long memberId) {
        log.info("secret key: {}", SECRET_KEY);
        StringBuilder sb = new StringBuilder();
        sb.append("Bearer ");
        sb.append(
                Jwts.builder()
                        .setSubject(memberId.toString())
                        .claim("memberId", memberId)
                        .claim("authorities", "MEMBER")
                        .setExpiration(new Date(System.currentTimeMillis() + ACCESS_TOKEN_EXPRIATION_TIME))
                        .signWith(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()), SignatureAlgorithm.HS256)
                        .compact());
        return sb.toString();
    }

    public boolean validateToken(ServletRequest request, String accessToken) {
        log.info("secret key: {}", SECRET_KEY);
        if (!accessToken.startsWith("Bearer ")) {
            return false;
        }
        try {
            return parseClaims(accessToken).getExpiration().after(new Date());
        } catch (io.jsonwebtoken.security.SecurityException | MalformedJwtException e) {
            log.error("잘못된 JWT 서명입니다.", e);
        } catch (UnsupportedJwtException e) {
            log.error("지원하지 않는 JWT 토큰입니다.", e);
        } catch (IllegalArgumentException e) {
            log.error("JWT 토큰이 없거나 잘못되었습니다.", e);
        } catch (ExpiredJwtException e) {
            log.error("만료된 JWT 토큰입니다.");
            request.setAttribute("exception", "ExpiredJwtException");
        }
        return false;
    }

    public Authentication getAuthentication(String accessToken) {
        Claims claims = parseClaims(accessToken);
        Collection<GrantedAuthority> authorities =
                Arrays.stream(claims.get("authorities").toString().split(","))
                        .map(SimpleGrantedAuthority::new)
                        .collect(Collectors.toList());
        UserDetails principal = new User(claims.getSubject(), "", authorities);
        return new UsernamePasswordAuthenticationToken(principal, accessToken, authorities);
    }

    private Claims parseClaims(String accessToken) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(SECRET_KEY.getBytes()))
                .build()
                .parseClaimsJws(accessToken.replace("Bearer ", ""))
                .getBody();
    }
}
