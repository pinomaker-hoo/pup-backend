package com.pup.api.walkingTrail.service;

import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailReviewSaveDto;
import com.pup.api.walkingTrail.event.vo.WalkingTrailReviewV0;
import com.pup.api.walkingTrail.repository.WalkingTrailReviewJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailReviewService {
    private final WalkingTrailReviewJpaRepository walkingTrailReviewJpaRepository;

    /**
     * 산책로 리뷰 저장
     */
    public void saveWalkingTrailReview(WalkingTrail walkingTrail, RequestWalkingTrailReviewSaveDto dto, User user) {
        walkingTrailReviewJpaRepository.save(
                walkingTrail.toWalkingTrailReview(dto.getRating(), dto.getTime(), user)
        );
    }

    /**
     * 산책로 리뷰 리스트 조회
     */
    public List<WalkingTrailReviewV0> findWalkingTrailReviewList(Integer userId) {
        return walkingTrailReviewJpaRepository.findWalkingTrailReviewList(userId);
    }
}
