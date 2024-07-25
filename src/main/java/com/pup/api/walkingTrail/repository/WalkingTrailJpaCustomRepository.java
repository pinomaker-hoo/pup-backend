package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.event.vo.WalkingTrailV0;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV1;
import com.pup.global.enums.WalkingTrailSearchTypeEnum;

import java.util.List;
import java.util.UUID;

public interface WalkingTrailJpaCustomRepository {
    List<WalkingTrailV0> findAllByUserId(Integer userId);

    List<WalkingTrailV1> findAllByWord(String word, WalkingTrailSearchTypeEnum searchType);

    WalkingTrailV1 findByWalkingTrailUid(UUID walkingTrailUid);
}
