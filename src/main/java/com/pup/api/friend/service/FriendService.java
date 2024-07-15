package com.pup.api.friend.service;

import com.pup.api.friend.domain.Friend;
import com.pup.api.friend.repository.FriendJpaRepository;
import com.pup.api.user.domain.User;
import com.pup.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class FriendService {
    private final FriendJpaRepository friendJpaRepository;


    /**
     * 친구 생성
     */
    public void saveFriend(User user, User targetUser) {
        friendJpaRepository.save(
                Friend.builder()
                        .user(user)
                        .friend(targetUser)
                        .build()
        );
    }

    /**
     * 친구 생성 유효성 체크
     */
    public void validationSaveFriend(Integer userId, Integer targetUserId) {
        if (userId == targetUserId) {
            throw new BadRequestException("자기 자신을 친구로 등록할 수 없습니다.");
        }

        existedFriend(userId, targetUserId);
    }

    /**
     * 이미 친구로 등록된 사용자인지 체크
     */
    private void existedFriend(Integer userId, Integer targetUserId) {
        boolean existedFriend = friendJpaRepository.existedFriend(userId, targetUserId);

        if (existedFriend) {
            throw new BadRequestException("이미 친구로 등록된 사용자 입니다.");
        }
    }
}
