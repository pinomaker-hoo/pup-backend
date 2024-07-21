package com.pup.api.walkingTrail.repository;


import java.util.UUID;

public interface WalkingTrailLikeJpaCustomRepository {
    Boolean existByUserAndWalkingTrail(Integer userId, UUID walkingTrailUid);
}
