package com.pup.api.walkingTrail.repository;


import com.pup.api.walkingTrail.event.vo.WalkingTrailV2;
import com.pup.global.enums.WalkingTrailSearchTypeEnum;

import java.util.List;
import java.util.UUID;

public interface WalkingTrailLikeJpaCustomRepository {
    Boolean existByUserAndWalkingTrail(Integer userId, UUID walkingTrailUid);

    List<WalkingTrailV2> findWalkingTrailLikeByUserId(Integer userId, WalkingTrailSearchTypeEnum type);
}
