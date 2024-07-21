package com.pup.api.walkingTrail.repository;

import java.util.List;

public interface WalkingTrailImageJpaCustomRepository {
    List<String> findWalkingTrailImageByWalkingTrailId(Long walkingTrailId);
}
