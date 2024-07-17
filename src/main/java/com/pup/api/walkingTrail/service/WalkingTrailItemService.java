package com.pup.api.walkingTrail.service;

import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailItem;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailItemSaveDto;
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

    /**
     * 산책 아이템 저장
     */
    public void saveWalkingTrialItem(WalkingTrail walkingTrail, RequestWalkingTrailItemSaveDto dto) {
        saveWalkingTrailItem(WalkingTrailItem.builder()
                .walkingTrail(walkingTrail)
                .lat(dto.getLat())
                .lng(dto.getLng())
                .build());
    }

    /**
     * 산책 아이템 저장
     */
    public WalkingTrailItem saveWalkingTrailItem(WalkingTrailItem walkingTrailItem) {
        return walkingTrailItemJpaRepository.save(walkingTrailItem);
    }
}
