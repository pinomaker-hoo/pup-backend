package com.pup.api.dog.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class DogJpaCustomRepositoryImpl implements DogJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}
