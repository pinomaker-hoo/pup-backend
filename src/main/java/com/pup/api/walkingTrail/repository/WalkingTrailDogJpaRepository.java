package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrailDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface WalkingTrailDogJpaRepository extends JpaRepository<WalkingTrailDog, Long>, WalkingTrailDogJpaCustomRepository {
    @Query("select wtd from WalkingTrailDog wtd join fetch wtd.walkingTrail wt where wt.walkingTrailId = ?1")
    List<WalkingTrailDog> findAllByWalkingTrailId(Long walkingTrailId);
}
