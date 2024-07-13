package com.pup.api.user.service;

import com.pup.api.user.event.dto.RequestTokenReissueDto;
import com.pup.global.dto.TokenDto;
import com.pup.global.dto.TokenSingleDto;
import com.pup.global.jwt.JwtTokenProvider;
import com.pup.global.jwt.JwtTokenValidator;
import com.pup.global.utils.EncryptionUtils;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class AuthService {
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;
    private final EncryptionUtils encryptionUtils;

    /**
     * Generate token
     */
    public TokenDto generateToken(Integer userId) {
        return jwtTokenProvider.issueToken(userId);
    }

    /**
     * 토큰 재발급
     */
    public TokenSingleDto reissueToken(RequestTokenReissueDto dto) {
        // ** RefreshToken 유효성 검사
        jwtTokenValidator.validateToken(dto.getRefreshToken());

        // ** 유저 정보 추출
        Claims claims = jwtTokenValidator.getClaimsFromToken(dto.getRefreshToken());

        String encodedUserId = String.valueOf(claims.get("userId"));

        // ** 정보 디코딩
        Integer decodedUserId = Integer.valueOf(encryptionUtils.decrypt(encodedUserId));

        // ** 토큰 생성
        TokenDto tokenDto = jwtTokenProvider.reissueToken(decodedUserId, dto.getRefreshToken());

        return tokenDto.toSingleTokenDto();
    }
}
