package com.pup.api.walkingTrail.service;

import com.pup.api.friend.event.vo.FriendV1;
import com.pup.api.friend.service.FriendService;
import com.pup.api.user.domain.User;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailSaveDto;
import com.pup.api.walkingTrail.event.dto.RequestWalkingTrailUpdateDto;
import com.pup.api.walkingTrail.event.vo.*;
import com.pup.api.walkingTrail.repository.WalkingTrailJpaRepository;
import com.pup.global.enums.OpenRangeEnum;
import com.pup.global.enums.WalkingTrailSearchTypeEnum;
import com.pup.global.exception.BadRequestException;
import com.pup.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailService {
    private final WalkingTrailJpaRepository walkingTrailJpaRepository;
    private final WalkingTrailItemService walkingTrailItemService;
    private final WalkingTrailDogService walkingTrailDogService;
    private final WalkingTrailLikeService walkingTrailLikeService;
    private final WalkingTrailImageService walkingTrailImageService;
    private final WalkingTrailReviewService walkingTrailReviewService;
    private final FriendService friendService;

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
        walkingTrail.toOver(dto.getName(), dto.getTime(), dto.getDistance(), dto.getDescription(), dto.getOpenRange(), dto.getMainImage());
        walkingTrailJpaRepository.save(walkingTrail);
    }

    /**
     * 산책로 조회
     */
    public WalkingTrail findOne(UUID walkingTrailUid) {
        return walkingTrailJpaRepository.findWalkingTrailByWalkingTrailUid(walkingTrailUid).orElseThrow(() -> new NotFoundException("산책로를 찾을 수 없습니다."));
    }

    /**
     * 산책로 삭제
     */
    @Transactional
    public void deleteWalkingTrail(List<Long> walkingTrailIdList) {
        walkingTrailDogService.deleteWalkingTrailDog(walkingTrailIdList);
        walkingTrailLikeService.deleteWalkingTrailLike(walkingTrailIdList);
        walkingTrailImageService.deleteWalkingTrailImage(walkingTrailIdList);
        walkingTrailItemService.deleteWalkingTrailItem(walkingTrailIdList);
        walkingTrailDogService.deleteWalkingTrailDog(walkingTrailIdList);
        walkingTrailJpaRepository.deleteWalkingTrail(walkingTrailIdList);
    }

    /**
     * 산책로 리스트 조회
     */
    public List<WalkingTrailV0Response> findAllByUserId(Integer userId) {
        List<WalkingTrailV0> list = walkingTrailJpaRepository.findAllByUserId(userId);
        return list.stream().map(item -> {
            List<WalkingTrailItemV0> itemList = walkingTrailItemService.findWalkingTrailItemByWalkingTrailId(item.getWalkingTrailId());
            return item.toResponse(itemList);
        }).toList();
    }

    public List<WalkingTrailV1Response> search(Integer userId, String word, WalkingTrailSearchTypeEnum type) {
        List<WalkingTrailV1> list = findAll(word, type);
        List<FriendV1> friendList = friendService.findFriendList(userId);

        List<WalkingTrailV1Response> filterList = list.stream().filter(
                item -> {
                    if (item.getOpenRange().equals(OpenRangeEnum.PUBLIC) || item.getUserId().equals(userId)) {
                        return true;
                    }

                    if (item.getOpenRange().equals(OpenRangeEnum.PRIVATE)) {
                        return false;
                    }

                    return friendList.stream().anyMatch(friend -> friend.getFriendUserId().equals(item.getUserId()));
                }
        ).map(item -> {
            Boolean isLike = walkingTrailLikeService.existByUserAndWalkingTrail(userId, item.getWalkingTrailUid());
            List<WalkingTrailItemV0> itemList = walkingTrailItemService.findWalkingTrailItemByWalkingTrailId(item.getWalkingTrailId());
            return item.toResponse(isLike, itemList);
        }).toList();


        if (type == WalkingTrailSearchTypeEnum.POPULAR) {
            return filterList.stream()
                    .sorted((a, b) -> Long.compare(b.getReviewCount(), a.getReviewCount()))
                    .collect(Collectors.toList());
        }

        if (type == WalkingTrailSearchTypeEnum.FAMOUS) {
            return filterList.stream()
                    .sorted((a, b) -> Long.compare(b.getReviewCount(), a.getReviewCount()))
                    .collect(Collectors.toList());
        }

        return filterList;
    }

    /**
     * 산책로 상세 조회
     */
    public WalkingTrailV1DetailResponse findOne(UUID walkingTrailUid, Integer userId) {
        WalkingTrailV1 walkingTrail = walkingTrailJpaRepository.findByWalkingTrailUid(walkingTrailUid);

        if (walkingTrail == null) {
            throw new NotFoundException("산책로를 찾을 수 없습니다.");
        }

        Boolean isLike = walkingTrailLikeService.existByUserAndWalkingTrail(userId, walkingTrailUid);
        List<String> imageList = walkingTrailImageService.findWalkingTrailImageByWalkingTrailId(walkingTrail.getWalkingTrailId());
        List<WalkingTrailItemV0> itemList = walkingTrailItemService.findWalkingTrailItemByWalkingTrailId(walkingTrail.getWalkingTrailId());

        return walkingTrail.toResponse(isLike, imageList, itemList);
    }

    /**
     * 산책로 리스트 조회
     */
    public List<WalkingTrailV1> findAll(String word, WalkingTrailSearchTypeEnum searchType) {
        return walkingTrailJpaRepository.findAllByWord(word, searchType);
    }
}
