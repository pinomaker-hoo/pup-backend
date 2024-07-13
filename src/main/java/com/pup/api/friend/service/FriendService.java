package com.pup.api.friend.service;

import com.pup.api.friend.repository.FriendJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class FriendService {
    private final FriendJpaRepository friendJpaRepository;
}
