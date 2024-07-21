package com.pup.api.walkingTrail.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WalkingTrailLikeJpaCustomRepositoryImpl implements WalkingTrailLikeJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}
