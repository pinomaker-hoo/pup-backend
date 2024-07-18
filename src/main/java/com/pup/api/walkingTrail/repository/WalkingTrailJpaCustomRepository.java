package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.event.vo.WalkingTrailV0;

import java.util.List;

public interface WalkingTrailJpaCustomRepository {
    List<WalkingTrailV0> findAllByUserId(Integer userId);
}
