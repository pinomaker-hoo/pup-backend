package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrail;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkingTrailJpaRepository extends JpaRepository<WalkingTrail, Long> {
}
