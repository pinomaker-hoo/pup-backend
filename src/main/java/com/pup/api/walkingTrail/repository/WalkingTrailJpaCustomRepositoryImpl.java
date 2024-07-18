package com.pup.api.walkingTrail.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WalkingTrailJpaCustomRepositoryImpl implements WalkingTrailJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}
