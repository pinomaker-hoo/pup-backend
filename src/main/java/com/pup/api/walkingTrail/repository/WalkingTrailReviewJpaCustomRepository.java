package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.event.vo.WalkingTrailReviewV0;

import java.util.List;

public interface WalkingTrailReviewJpaCustomRepository {
    List<WalkingTrailReviewV0> findWalkingTrailReviewList(Integer userId);
}
