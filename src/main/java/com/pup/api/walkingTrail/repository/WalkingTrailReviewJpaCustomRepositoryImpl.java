package com.pup.api.walkingTrail.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WalkingTrailReviewJpaCustomRepositoryImpl implements WalkingTrailReviewJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}