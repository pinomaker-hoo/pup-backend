package com.pup.api.dog.service;

import com.pup.api.dog.domain.Dog;
import com.pup.api.dog.event.dto.RequestDogSaveDto;
import com.pup.api.dog.event.dto.SaveDog;
import com.pup.api.dog.repository.DogJpaRepository;
import com.pup.api.user.domain.User;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
