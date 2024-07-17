package com.pup.api.walkingTrail.service;

import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.repository.WalkingTrailImageJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailImageService {
    private final WalkingTrailImageJpaRepository walkingTrailImageJpaRepository;

    /**
     * 산책로 이미지 저장
     */
    public void saveWalkingTrailImage(WalkingTrail walkingTrail, Float lat, Float lng, String path) {
        walkingTrailImageJpaRepository.save(walkingTrail.toWalkingTrailImage(lat, lng, path));
    }
}
