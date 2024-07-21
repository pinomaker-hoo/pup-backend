package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailDog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface WalkingTrailJpaRepository extends JpaRepository<WalkingTrail, Long>, WalkingTrailJpaCustomRepository {
    Optional<WalkingTrail> findWalkingTrailByWalkingTrailUid(UUID walkingTrailUid);

    @Modifying
    @Transactional(rollbackFor = {Exception.class})
    @Query("DELETE WalkingTrail wt WHERE wt.walkingTrailId IN(:walkingTrailIdList)")
    int deleteWalkingTrail(List<Long> walkingTrailIdList);

    @Query("SELECT wt FROM WalkingTrail wt JOIN FETCH wt.user")
    List<WalkingTrail> findAllWithUser();
}
