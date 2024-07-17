package com.pup.api.walkingTrail.service;

import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailSaveDto;
import com.pup.api.walkingTrail.repository.WalkingTrailJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailService {
    private final WalkingTrailJpaRepository walkingTrailJpaRepository;
    private final WalkingTrailItemService walkingTrailItemService;
    private final WalkingTrailDogService walkingTrailDogService;

    /**
     * 산책로 최초 저장
     */
    @Transactional(rollbackFor = Exception.class)
    public WalkingTrail saveWalkingTrail(User user, RequestWalkingTrailSaveDto dto) {
        WalkingTrail a = WalkingTrail.builder().user(user).build();
        log.info("WalkingTrailService.saveWalkingTrail : {}", a.toString());
        WalkingTrail walkingTrail = walkingTrailJpaRepository.save(WalkingTrail.builder().user(user).build());

        walkingTrailItemService.saveWalkingTrailItem(walkingTrail.toWalkingTrailItem(dto.getLat(), dto.getLng()));
        walkingTrailDogService.saveWalkingTrailDogList(walkingTrail, dto.getDogIdList());

        return walkingTrail;
    }
}
