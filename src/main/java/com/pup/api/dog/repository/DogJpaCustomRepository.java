package com.pup.api.dog.repository;


import com.pup.api.dog.event.vo.DogV0;

import java.util.List;

public interface DogJpaCustomRepository {
    List<DogV0> findDogListByUserId(Integer userId);

    Boolean existsByDogId(Long dogId);
}
