package com.pup.api.walkingTrail.service;

import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailSaveDto;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailUpdateDto;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV0;
import com.pup.api.walkingTrail.repository.WalkingTrailJpaRepository;
import com.pup.global.exception.BadRequestException;
import com.pup.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

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

    /**
     * 산책로 활성화 검증
     */
    public void validationWalkingTrail(WalkingTrail walkingTrail) {
        if (walkingTrail.getIsEnabled()) {
            throw new BadRequestException("이미 활성화된 산책로입니다.");
        }
    }

    /**
     * 산책로 활성화
     */
    public void walkingTrailToEnable(WalkingTrail walkingTrail, RequestWalkingTrailUpdateDto dto) {
        walkingTrail.toOver(dto.getName(), dto.getTime(), dto.getDistance(), dto.getDescription(), dto.getOpenRange());
        walkingTrailJpaRepository.save(walkingTrail);
    }

    /**
     * 산책로 노출
     */
    public void walkingTrailToExpose(WalkingTrail walkingTrail) {
        walkingTrail.toExpose();
        walkingTrailJpaRepository.save(walkingTrail);
    }

    /**
     * 산책로 조회
     */
    public WalkingTrail findOne(UUID walkingTrailUid) {
        return walkingTrailJpaRepository.findWalkingTrailByWalkingTrailUid(walkingTrailUid).orElseThrow(() -> new NotFoundException("산책로를 찾을 수 없습니다."));
    }

    /**
     * 산책로 리스트 조회
     */
    public List<WalkingTrailV0> findAllByUserId(Integer userId) {
        return walkingTrailJpaRepository.findAllByUserId(userId);
    }
}
