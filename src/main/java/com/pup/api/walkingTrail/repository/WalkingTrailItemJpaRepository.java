package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WalkingTrailItemJpaRepository extends JpaRepository<WalkingTrailItem, Long>, WalkingTrailItemJpaCustomRepository {
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("DELETE WalkingTrailItem wti WHERE wti.walkingTrail.walkingTrailId IN(:walkingTrailIdList)")
    int deleteWalkingTrailItem(List<Long> walkingTrailIdList);
}
