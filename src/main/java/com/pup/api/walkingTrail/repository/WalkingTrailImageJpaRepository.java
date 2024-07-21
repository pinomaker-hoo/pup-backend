package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailImage;
import org.springframework.data.jpa.repository.JpaRepository;

public interface WalkingTrailImageJpaRepository extends JpaRepository<WalkingTrailImage, Long>, WalkingTrailImageJpaCustomRepository {
}
