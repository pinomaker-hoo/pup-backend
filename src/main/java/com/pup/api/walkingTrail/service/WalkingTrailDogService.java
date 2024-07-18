package com.pup.api.walkingTrail.service;

import com.pup.api.dog.domain.Dog;
import com.pup.api.dog.service.DogService;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailDog;
import com.pup.api.walkingTrail.repository.WalkingTrailDogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class WalkingTrailDogService {
    private final DogService dogService;
    private final WalkingTrailDogJpaRepository walkingTrailDogJpaRepository;


    /**
     * 산책로에 강아지 리스트 저장
     */
    public void saveWalkingTrailDogList(WalkingTrail walkingTrail, List<Long> dogIdList) {
        for (Long dogId : dogIdList) {
            saveWalkingTrailDog(walkingTrail, dogId);
        }
    }

    /**
     * 산책로에 강아지 저장
     */
    public WalkingTrailDog saveWalkingTrailDog(WalkingTrail walkingTrail, Long dogId) {
        Dog dog = dogService.findOne(dogId);
        return walkingTrailDogJpaRepository.save(walkingTrail.toWalkingTrailDog(dog));
    }

    /**
     * 산책로에 강아지 변경
     */
    public void changeWalkingTrailDog(WalkingTrail walkingTrail, List<Long> dogIdList) {
        List<WalkingTrailDog> walkingTrailDogList = findAll(walkingTrail.getWalkingTrailId());

        for (WalkingTrailDog walkingTrailDog : walkingTrailDogList) {
            if (dogIdList.contains(walkingTrailDog.getDog().getDogId())) {
                dogIdList.remove(walkingTrailDog.getDog().getDogId());
                continue;
            }
            walkingTrailDogJpaRepository.delete(walkingTrailDog);
        }

        for (Long dogId : dogIdList) {
            saveWalkingTrailDog(walkingTrail, dogId);
        }
    }

    /**
     * 산책로에 강아지 조회
     */
    public List<WalkingTrailDog> findAll(Long walkingTrailId) {
        return walkingTrailDogJpaRepository.findAllByWalkingTrailId(walkingTrailId);
    }
}
