package com.justsayit.member.service.auth.apple;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.justsayit.member.exception.FailToGetApplePublicKey;
import com.justsayit.member.exception.FailToVerifyAppleIdentityToken;
import com.justsayit.member.exception.InvalidAppleIdentityToken;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Base64;
import java.util.Map;

@Component
@RequiredArgsConstructor
public final class AppleJwtUtils {

    @Value("${oauth.apple.client-id}")
    private String CLIENT_ID;
    @Value("${oauth.apple.issuer}")
    private String ISSUER;
    private final String KID = "kid";
    private final String ALG = "alg";
    private final String CHARSET_NAME = "UTF-8";
    private final String DELIMITER_POINT = ".";

    public Claims verifyIdentityTokenAndGetPayload(String identityToken, ApplePublicKeyResponse response) {
        try {
            /* JWT header */
            Map<String, String> header = new ObjectMapper().readValue(base64Decode(getHeaderOfIdentityToken(identityToken)), Map.class);
            ApplePublicKeyResponse.Key key = findPublicKey(r다esponse, header);

            /* RSA 복호화를 위한 공개키 생성 */
            byte[] nBytes = Base64.getUrlDecoder().decode(key.getN());
            byte[] eBytes = Base64.getUrlDecoder().decode(key.getE());
            BigInteger n = new BigInteger(1, nBytes);
            BigInteger e = new BigInteger(1, eBytes);
            RSAPublicKeySpec publicKeySpec = new RSAPublicKeySpec(n, e);
            KeyFactory keyFactory = KeyFactory.getInstance(key.getKty());
            PublicKey publicKey = keyFactory.generatePublic(publicKeySpec);

            /* JWT payload */
            Claims body = Jwts.parserBuilder()
                    .setSigningKey(publicKey)
                    .build()
                    .parseClaimsJws(identityToken)
                    .getBody();

            /* 토큰 검증 */
            if (!(body.getIssuer().equals(ISSUER) && body.getAudience().equals(CLIENT_ID))) {
                throw new InvalidAppleIdentityToken();
            }

            /* payload 반환 */
            return Jwts.parserBuilder().setSigningKey(publicKey).build().parseClaimsJws(identityToken).getBody();
        } catch (Exception e) {
            throw new FailToVerifyAppleIdentityToken();
        }
    }

    private ApplePublicKeyResponse.Key findPublicKey(ApplePublicKeyResponse response, Map<String, String> header) {
        return response.getKeys().stream()
                .filter(key -> key.getKid().equals(header.get(KID)) && key.getAlg().equals(header.get(ALG)))
                .findFirst()
                .orElseThrow(FailToGetApplePublicKey::new);
    }

    private String base64Decode(String identityToken) throws UnsupportedEncodingException {
        return new String(Base64.getDecoder().decode(identityToken), CHARSET_NAME);
    }

    private String getHeaderOfIdentityToken(String identityToken) {
        return identityToken.substring(0, identityToken.indexOf(DELIMITER_POINT));
    }
}
