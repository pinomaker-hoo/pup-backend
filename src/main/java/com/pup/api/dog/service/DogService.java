package com.pup.api.dog.service;

import com.pup.api.dog.repository.DogJpaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Slf4j
@Service
public class DogService {
    private final DogJpaRepository dogJpaRepository;

}
