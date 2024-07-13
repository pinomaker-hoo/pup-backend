package com.pup.api.user.service;

import com.pup.api.user.domain.User;
import com.pup.api.user.domain.UserSocialTypeEnum;
import com.pup.api.user.event.dto.RequestUserLoginDto;
import com.pup.api.user.repository.UserJpaRepository;
import com.pup.global.enums.OpenRangeEnum;
import io.jsonwebtoken.Claims;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pup.api.user.event.dto.RequestTokenReissueDto;
import com.pup.api.user.event.dto.RequestUserSaveDto;
import com.pup.api.user.event.vo.LoginUser;
import com.pup.global.dto.CommonResponse;
import com.pup.global.dto.TokenDto;
import com.pup.global.enums.UserRole;
import com.pup.global.exception.BadRequestException;
import com.pup.global.exception.NotFoundException;
import com.pup.global.jwt.JwtTokenProvider;
import com.pup.global.jwt.JwtTokenValidator;
import com.pup.global.utils.EncryptionUtils;

import java.util.UUID;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenProvider jwtTokenProvider;
    private final JwtTokenValidator jwtTokenValidator;
    private final EncryptionUtils encryptionUtils;


    /**
     * PUP 유저 생성
     */
    public User saveUser(RequestUserSaveDto dto) {
        return userJpaRepository.save(dto.toUser(passwordEncoder.encode(dto.getPassword())));
    }

    /**
     * 이메일 중복 체크
     */
    public void existedUserByEmail(String email) {
        boolean existUserByPhoneNumber = userJpaRepository.existedUserByEmail(email);

        if (existUserByPhoneNumber) {
            throw new BadRequestException("이미 사용 중인 이메일 입니다.");
        }
    }

    /**
     * 로그인 유저 조회
     */
    public LoginUser findOne(String email) {
        LoginUser user = userJpaRepository.findUserByEmail(email);

        if (user == null) {
            throw new NotFoundException("존재하지 않는 사용자 입니다.");
        }

        return user;
    }

    /**
     * 비밀번호 검증
     */
    public void validationPassword(LoginUser loginUser, String password) {
        if (!passwordEncoder.matches(password, loginUser.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }
    }
}
