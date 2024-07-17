package com.pup.api.walkingTrail.service;

import com.pup.api.walkingTrail.repository.WalkingTrailDogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailDogService {
    private final WalkingTrailDogJpaRepository walkingTrailDogJpaRepository;
}
