package com.pup.api.dog.service;

import com.pup.api.dog.domain.Dog;
import com.pup.api.dog.event.dto.RequestDogSaveDto;
import com.pup.api.dog.event.dto.RequestDogUpdateDto;
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

    /**
     * 강아지 저장
     */
    @Transactional(rollbackFor = Exception.class)
    public void saveDogList(User user, RequestDogSaveDto dto) {
        for (SaveDog dog : dto.getDogList()) {
            saveDog(user, dog);
        }
    }

    /**
     * 강아지 저장 시 유효성 검사
     */
    public void validationSaveDog(User user, Integer savedDogSize) {
        long dogCount = findDogCount(user);
        if (dogCount + savedDogSize > 4) {
            throw new IllegalArgumentException("강아지는 최대 4마리까지 등록 가능합니다.");
        }
    }

    /**
     * 유저 아이디로 강아지 수 조회
     */
    public long findDogCount(User user) {
        return dogJpaRepository.countByUser(user);
    }

    /**
     * 강아지 존재 여부 확인
     */
    public void existByDogId(Long dogId) {
        Boolean existsByDogId = dogJpaRepository.existsByDogId(dogId);
        if (!existsByDogId) {
            throw new NotFoundException("강아지를 찾을 수 없습니다.");
        }
    }

    /**
     * 강아지 삭제
     */
    public void deleteDog(Long dogId) {
        dogJpaRepository.deleteById(dogId);
    }

    /**
     * 강아지 조회
     */
    public Dog findOne(Long dogId) {
        return dogJpaRepository.findById(dogId).orElseThrow(() -> new NotFoundException("해당 강아지가 없습니다."));
    }

    /**
     * 강아지 정보 수정
     */
    public void updateDog(Dog dog, RequestDogUpdateDto dto) {
        dog.changeDog(dto.getName(), dto.getProfile(), dto.getBirth(), dto.getIsNeutered());
        dogJpaRepository.save(dog);
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
