package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailLike;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkingTrailLikeJpaRepository extends JpaRepository<WalkingTrailLike, Long>, WalkingTrailLikeJpaCustomRepository {
}
