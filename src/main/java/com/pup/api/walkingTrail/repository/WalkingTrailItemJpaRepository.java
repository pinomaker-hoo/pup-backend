package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkingTrailItemJpaRepository extends JpaRepository<WalkingTrailItem, Long>, WalkingTrailItemJpaCustomRepository {
}
