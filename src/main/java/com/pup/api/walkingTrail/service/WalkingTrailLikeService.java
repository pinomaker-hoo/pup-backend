package com.pup.api.walkingTrail.service;

import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailLike;
import com.pup.api.walkingTrail.repository.WalkingTrailLikeJpaRepository;
import com.pup.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.UUID;


@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailLikeService {
    private final WalkingTrailLikeJpaRepository walkingTrailLikeJpaRepository;

    /**
     * 사용자가 해당 산책로에 좋아요를 누르거나 취소
     */
    public void saveWalkingTrailLike(User user, WalkingTrail walkingTrail, Boolean like) {
        Boolean exist = existByUserAndWalkingTrail(user.getUserId(), walkingTrail.getWalkingTrailUid());
        if (like) {
            if (exist) {
                throw new BadRequestException("이미 좋아요를 누르셨습니다.");
            }

            // 좋아요 추가
            walkingTrailLikeJpaRepository.save(
                    WalkingTrailLike.builder()
                            .user(user)
                            .walkingTrail(walkingTrail)
                            .build());
            return;
        }

        if (!exist) {
            throw new BadRequestException("좋아요를 누르지 않았습니다.");
        }

        walkingTrailLikeJpaRepository.deleteWalkingTrailLike(user.getUserId(), walkingTrail.getWalkingTrailId());
    }

    /**
     * 사용자가 해당 산책로에 좋아요를 눌렀는지 확인
     */
    public Boolean existByUserAndWalkingTrail(Integer userId, UUID walkingTrailUid) {
        return walkingTrailLikeJpaRepository.existByUserAndWalkingTrail(userId, walkingTrailUid);
    }
}
