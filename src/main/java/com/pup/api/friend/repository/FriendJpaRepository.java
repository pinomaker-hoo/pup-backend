package com.pup.api.friend.repository;

import com.pup.api.friend.domain.Friend;
import org.springframework.data.jpa.repository.JpaRepository;

public interface FriendJpaRepository extends JpaRepository<Friend, Long>, FriendJpaCustomRepository {
}
