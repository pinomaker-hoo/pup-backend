package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.event.vo.WalkingTrailItemV0;

import java.util.List;

public interface WalkingTrailItemJpaCustomRepository {
    List<WalkingTrailItemV0> findWalkingTrailItemV0ByWalkingTrailId(Long walkingTrailId);
}
