package com.pup.api.walkingTrail.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WalkingTrailImageJpaCustomRepositoryImpl implements WalkingTrailImageJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}
