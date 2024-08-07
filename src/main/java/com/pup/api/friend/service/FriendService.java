package com.pup.api.friend.service;

import com.pup.api.friend.domain.Friend;
import com.pup.api.friend.event.vo.FriendV0;
import com.pup.api.friend.event.vo.FriendV1;
import com.pup.api.friend.repository.FriendJpaRepository;
import com.pup.api.user.domain.User;
import com.pup.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

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
     * 친구 리스트 조회
     */
    public List<FriendV0> findFriendList(Integer userId, String name) {
        List<FriendV0> list = friendJpaRepository.findFriendList(userId, name);

        return list.stream().filter(item -> item.getUserUid().toString().contains(name)).toList();
    }

    /**
     * 친구 간단 리스트 조회
     */
    public List<FriendV1> findFriendList(Integer userId) {
        return friendJpaRepository.findFriendListV1(userId);
    }

    /**
     * 친구 삭제
     */
    public void deleteFriend(List<Long> friendIdList) {
        friendJpaRepository.deleteFriend(friendIdList);
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
