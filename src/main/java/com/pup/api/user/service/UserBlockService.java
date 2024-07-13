package com.pup.api.user.service;

import com.pup.api.user.repository.UserBlockJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class UserBlockService {
    private final UserBlockJpaRepository userBlockJpaRepository;

}
