package com.pup.api.friend.repository;


import com.pup.api.friend.event.vo.FriendV0;
import com.pup.api.friend.event.vo.FriendV1;

import java.util.List;

public interface FriendJpaCustomRepository {
    Boolean existedFriend(Integer userId, Integer targetUserId);

    List<FriendV0> findFriendList(Integer userId, String name);

    List<FriendV1> findFriendListV1(Integer userId);
}
