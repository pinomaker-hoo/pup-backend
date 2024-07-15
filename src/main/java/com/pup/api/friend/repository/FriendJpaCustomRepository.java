package com.pup.api.friend.repository;


public interface FriendJpaCustomRepository {
    Boolean existedFriend(Integer userId, Integer targetUserId);
}
