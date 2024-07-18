package com.pup.api.walkingTrail.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class WalkingTrailItemJpaCustomRepositoryImpl implements WalkingTrailItemJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}
