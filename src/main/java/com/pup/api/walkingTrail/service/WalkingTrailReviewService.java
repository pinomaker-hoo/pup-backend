package com.pup.api.walkingTrail.service;

import com.pup.api.friend.event.vo.FriendV1;
import com.pup.api.friend.service.FriendService;
import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailReviewSaveDto;
import com.pup.api.walkingTrail.event.vo.WalkingTrailItemV0;
import com.pup.api.walkingTrail.event.vo.WalkingTrailReviewResponse;
import com.pup.api.walkingTrail.event.vo.WalkingTrailReviewV0;
import com.pup.api.walkingTrail.repository.WalkingTrailReviewJpaRepository;
import com.pup.global.enums.OpenRangeEnum;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailReviewService {
    private final WalkingTrailReviewJpaRepository walkingTrailReviewJpaRepository;
    private final WalkingTrailItemService walkingTrailItemService;
    private final FriendService friendService;

    /**
     * 산책로 리뷰 저장
     */
    public void saveWalkingTrailReview(WalkingTrail walkingTrail, RequestWalkingTrailReviewSaveDto dto, User user) {
        walkingTrailReviewJpaRepository.save(
                walkingTrail.toWalkingTrailReview(dto.getRating(), dto.getTime(), user)
        );
    }

    /**
     * 유저의 산책로 리뷰 리스트 조회
     */
    public List<WalkingTrailReviewResponse> findWalkingTrailReviewListByUser(User targetUser, Integer userId) {
        if (targetUser.getOpenRange() == OpenRangeEnum.PRIVATE) {
            return new ArrayList<>();
        }

        if (targetUser.getOpenRange() == OpenRangeEnum.PROTECTED) {
            List<FriendV1> friendList = friendService.findFriendList(targetUser.getUserId());

            if (friendList.stream().noneMatch(friend -> friend.getUserId().equals(userId))) {
                return new ArrayList<>();
            }
        }

        return findWalkingTrailReviewList(targetUser.getUserId())
                .stream()
                .map(item -> {
                    List<WalkingTrailItemV0> itemList = walkingTrailItemService.findWalkingTrailItemByWalkingTrailId(item.getWalkingTrailId());
                    return item.toResponse(itemList);
                })
                .toList();
    }

    /**
     * 산책로 리뷰 리스트 조회
     */
    public List<WalkingTrailReviewV0> findWalkingTrailReviewList(Integer userId) {
        return walkingTrailReviewJpaRepository.findWalkingTrailReviewList(userId);
    }
}
