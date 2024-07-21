package com.pup.api.walkingTrail.service;

import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailItem;
import com.pup.api.walkingTrail.event.dto.Place;
import com.pup.api.walkingTrail.repository.WalkingTrailItemJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailItemService {
    private final WalkingTrailItemJpaRepository walkingTrailItemJpaRepository;

    /**
     * 산책 아이템 저장
     */
    public void saveWalkingTrialItem(WalkingTrail walkingTrail, List<Place> placeList) {
        for (Place place : placeList) {
            saveWalkingTrailItem(WalkingTrailItem.builder()
                    .walkingTrail(walkingTrail)
                    .lat(place.getLat())
                    .lng(place.getLng())
                    .build());
        }
    }

    /**
     * 산책 아이템 저장
     */
    public WalkingTrailItem saveWalkingTrailItem(WalkingTrailItem walkingTrailItem) {
        return walkingTrailItemJpaRepository.save(walkingTrailItem);
    }
}
