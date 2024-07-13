package com.pup.global.jwt;

import com.pup.global.utils.EncryptionUtils;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.pup.global.dto.TokenDto;

import java.security.PrivateKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtTokenProvider {

    private final JwtConfig jwtConfig;
    private final EncryptionUtils encryptionUtils;

    @Value("${jwt.accessTokenValidityInSeconds}")
    private String accessTokenValidationTime;

    @Value("${jwt.refreshTokenValidityInSeconds}")
    private String refreshTokenValidationTime;

    public TokenDto issueToken(Integer userId) {
        final String accessToken = generateToken(userId, Long.valueOf(accessTokenValidationTime));
        final String refreshToken = generateToken(userId, Long.valueOf(refreshTokenValidationTime));

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    public TokenDto reissueToken(Integer userId, String refreshToken) {
        final String accessToken = generateToken(userId, Long.valueOf(accessTokenValidationTime));

        return TokenDto.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private String generateToken(Integer userId, Long tokenValidationTime) {
        final Map<String, Object> claims = createClaims(userId);
        final PrivateKey privateKey = jwtConfig.getPrivateKey();

        return Jwts.builder()
                .addClaims(claims)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + tokenValidationTime * 1000))
                .signWith(privateKey, SignatureAlgorithm.RS256)
                .compact();
    }

    private Map<String, Object> createClaims(Integer userId) {
        Map<String, Object> claims = new HashMap<>();
        String encodedId = encryptionUtils.encrypt(String.valueOf(userId));

        claims.put("userId", encodedId);

        return claims;
    }
}
