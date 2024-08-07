package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailImage;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface WalkingTrailImageJpaRepository extends JpaRepository<WalkingTrailImage, Long>, WalkingTrailImageJpaCustomRepository {
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("DELETE WalkingTrailImage wti WHERE wti.walkingTrail.walkingTrailId IN(:walkingTrailIdList)")
    int deleteWalkingTrailImage(List<Long> walkingTrailIdList);
}
