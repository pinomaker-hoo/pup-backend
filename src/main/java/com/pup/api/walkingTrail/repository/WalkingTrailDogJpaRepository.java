package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailDog;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkingTrailDogJpaRepository extends JpaRepository<WalkingTrailDog, Long> {
}
