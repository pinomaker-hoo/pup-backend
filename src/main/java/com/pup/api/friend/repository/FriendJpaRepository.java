package com.pup.api.friend.repository;

import com.pup.api.friend.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface FriendJpaRepository extends JpaRepository<Friend, Long>, FriendJpaCustomRepository {
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("DELETE Friend f WHERE f.friendId IN(:friendId)")
    int deleteFriend(List<Long> friendId);
}
