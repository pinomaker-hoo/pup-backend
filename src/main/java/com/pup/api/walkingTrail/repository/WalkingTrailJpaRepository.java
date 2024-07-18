package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrail;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.UUID;

public interface WalkingTrailJpaRepository extends JpaRepository<WalkingTrail, Long>, WalkingTrailJpaCustomRepository {
    Optional<WalkingTrail> findWalkingTrailByWalkingTrailUid(UUID walkingTrailUid);
}
