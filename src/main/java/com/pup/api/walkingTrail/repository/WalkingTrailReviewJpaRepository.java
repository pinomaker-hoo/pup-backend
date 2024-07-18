package com.pup.api.walkingTrail.repository;


import com.pup.api.walkingTrail.domain.WalkingTrailReview;
import org.springframework.data.jpa.repository.JpaRepository;


public interface WalkingTrailReviewJpaRepository extends JpaRepository<WalkingTrailReview, Long>, WalkingTrailReviewJpaCustomRepository {
}
