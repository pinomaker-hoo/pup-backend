package com.pup.api.walkingTrail.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WalkingTrailDogJpaCustomRepositoryImpl implements WalkingTrailDogJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}
