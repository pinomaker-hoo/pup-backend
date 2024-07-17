package com.pup.api.walkingTrail.service;

import com.pup.api.walkingTrail.repository.WalkingTrailItemJpaRepository;
import com.pup.api.walkingTrail.repository.WalkingTrailJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailItemService {
    private final WalkingTrailItemJpaRepository walkingTrailItemJpaRepository;
}
