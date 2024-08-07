package com.pup.api.walkingTrail.repository;


import com.pup.api.walkingTrail.domain.WalkingTrailReview;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface WalkingTrailReviewJpaRepository extends JpaRepository<WalkingTrailReview, Long>, WalkingTrailReviewJpaCustomRepository {
    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("DELETE WalkingTrailReview wtr WHERE wtr.walkingTrail.walkingTrailId IN(:walkingTrailIdList)")
    int deleteWalkingTrailReview(List<Long> walkingTrailIdList);
}
