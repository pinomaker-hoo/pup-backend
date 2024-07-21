package com.pup.api.user.service;

import com.pup.api.friend.event.vo.FriendV1;
import com.pup.api.friend.service.FriendService;
import com.pup.api.user.domain.User;
import com.pup.api.user.event.dto.RequestUserUpdateDto;
import com.pup.api.user.event.vo.UserV0;
import com.pup.api.user.event.vo.UserV1;
import com.pup.api.user.repository.UserJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import com.pup.api.user.event.dto.RequestUserSaveDto;
import com.pup.api.user.event.vo.LoginUser;
import com.pup.global.exception.BadRequestException;
import com.pup.global.exception.NotFoundException;
import com.pup.global.jwt.JwtTokenProvider;
import com.pup.global.jwt.JwtTokenValidator;
import com.pup.global.utils.EncryptionUtils;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserService {
    private final UserJpaRepository userJpaRepository;
    private final PasswordEncoder passwordEncoder;
    private final FriendService friendService;
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
     * 비밀번호 수정
     */
    public void updatePassword(User user, String password) {
        user.changePassword(passwordEncoder.encode(password));
        userJpaRepository.save(user);
    }

    /**
     * 추가할 사용자 조회
     */
    public List<UserV1> findUserList(Integer userId, String userUid) {
        List<FriendV1> friendList = friendService.findFriendList(userId);
        List<UserV1> list = userJpaRepository.findUserList(userId);

        List<UserV1> filterList = list.stream().filter(item ->
                friendList.stream().noneMatch(friend -> friend.getFriendUserId().equals(item.getUserId()))
        ).toList();

        return filterList;
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
     * 사용자 조회
     */
    public User findOne(Integer userId) {
        return userJpaRepository.findById(userId)
                .orElseThrow(() -> new NotFoundException("존재하지 않는 사용자 입니다."));
    }

    /**
     * 마지막 산책 시간 수정
     */
    public User updateLastWakingDate(User user) {
        user.changeLastWakingDate();
        return userJpaRepository.save(user);
    }


    /**
     * 사용자 정보 수정
     */
    public void updateUser(User user, RequestUserUpdateDto dto) {
        user.changeUser(dto.getProfile(), dto.getDescription());
        userJpaRepository.save(user);
    }

    /**
     * 사용자 조회
     */
    public UserV0 findUserV0One(Integer userId) {
        UserV0 user = userJpaRepository.findUserByUserId(userId);

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

    /**
     * 비밀번호 검증
     */
    public void validationPassword(User user, String password) {
        if (!passwordEncoder.matches(password, user.getPassword())) {
            throw new BadRequestException("비밀번호가 일치하지 않습니다.");
        }
    }
}
