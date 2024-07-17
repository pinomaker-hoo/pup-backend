package com.pup.api.dog.service;

import com.pup.api.dog.domain.Dog;
import com.pup.api.dog.event.dto.RequestDogSaveDto;
import com.pup.api.dog.event.dto.SaveDog;
import com.pup.api.dog.event.vo.DogV0;
import com.pup.api.dog.repository.DogJpaRepository;
import com.pup.api.user.domain.User;
import com.pup.global.exception.NotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@RequiredArgsConstructor
@Slf4j
@Service
public class DogService {
    private final DogJpaRepository dogJpaRepository;

    @Transactional(rollbackFor = Exception.class)
    public void saveDogList(User user, RequestDogSaveDto dto) {
        for (SaveDog dog : dto.getDogList()) {
            saveDog(user, dog);
        }
    }

    /**
     * 강아지 조회
     */
    public Dog findOne(Long dogId) {
        return dogJpaRepository.findById(dogId).orElseThrow(() -> new NotFoundException("해당 강아지가 없습니다."));
    }

    /**
     * 유저 아이디로 강아지 리스트 조회
     */
    public List<DogV0> findDogListByUserId(Integer userId) {
        return dogJpaRepository.findDogListByUserId(userId);
    }

    /**
     * 강아지 정보 저장
     */
    private void saveDog(User user, SaveDog dog) {
        dogJpaRepository.save(Dog.builder()
                .name(dog.getName())
                .profile(dog.getProfile())
                .birth(dog.getBirth())
                .isNeutered(dog.getIsNeutered())
                .user(user)
                .build());
    }
}
