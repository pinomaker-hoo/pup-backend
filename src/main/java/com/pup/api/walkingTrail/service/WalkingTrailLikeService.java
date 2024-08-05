package com.pup.api.walkingTrail.service;

import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailLike;
import com.pup.api.walkingTrail.event.vo.WalkingTrailItemV0;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV2;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV2Response;
import com.pup.api.walkingTrail.repository.WalkingTrailLikeJpaRepository;
import com.pup.global.enums.WalkingTrailSearchTypeEnum;
import com.pup.global.exception.BadRequestException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;


@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailLikeService {
    private final WalkingTrailItemService walkingTrailItemService;
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

        walkingTrailLikeJpaRepository.deleteWalkingTrailLike(user.getUserId(), walkingTrail.getWalkingTrailUid());
    }

    /**
     * 사용자가 해당 산책로에 좋아요를 눌렀는지 확인
     */
    public Boolean existByUserAndWalkingTrail(Integer userId, UUID walkingTrailUid) {
        return walkingTrailLikeJpaRepository.existByUserAndWalkingTrail(userId, walkingTrailUid);
    }

    /**
     * 사용자가 좋아요를 누른 산책로 목록 조회
     */
    public List<WalkingTrailV2Response> findWalkingTrailLikeByUserId(Integer userId, WalkingTrailSearchTypeEnum type) {
        List<WalkingTrailV2> list = walkingTrailLikeJpaRepository.findWalkingTrailLikeByUserId(userId, type);

        if (type == WalkingTrailSearchTypeEnum.POPULAR) {
            return list.stream()
                    .map(item -> {
                                List<WalkingTrailItemV0> itemList = walkingTrailItemService.findWalkingTrailItemByWalkingTrailId(item.getWalkingTrailId());
                                return item.toResponse(itemList);
                            }
                    )
                    .sorted((a, b) -> Long.compare(b.getReviewCount(), a.getReviewCount()))
                    .collect(Collectors.toList());
        }

        if (type == WalkingTrailSearchTypeEnum.FAMOUS) {
            return list.stream()
                    .map(item -> {
                                List<WalkingTrailItemV0> itemList = walkingTrailItemService.findWalkingTrailItemByWalkingTrailId(item.getWalkingTrailId());
                                return item.toResponse(itemList);
                            }
                    )
                    .sorted((a, b) -> Long.compare(b.getReviewCount(), a.getReviewCount()))
                    .collect(Collectors.toList());
        }

        return list.stream().map(
                item -> {
                    List<WalkingTrailItemV0> itemList = walkingTrailItemService.findWalkingTrailItemByWalkingTrailId(item.getWalkingTrailId());
                    return item.toResponse(itemList);
                }
        ).toList();
    }
}
