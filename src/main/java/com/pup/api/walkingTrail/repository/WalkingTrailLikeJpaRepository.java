package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailLike;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;


public interface WalkingTrailLikeJpaRepository extends JpaRepository<WalkingTrailLike, Long>, WalkingTrailLikeJpaCustomRepository {
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("DELETE WalkingTrailLike wtl WHERE wtl.user.userId = ?1 and wtl.walkingTrail.walkingTrailUid = ?2")
    int deleteWalkingTrailLike(Integer userId, UUID walkingTrailUid);

    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("DELETE WalkingTrailLike wtl WHERE wtl.walkingTrail.walkingTrailId IN(:walkingTrailIdList)")
    int deleteWalkingTrailLike(List<Long> walkingTrailIdList);
}
