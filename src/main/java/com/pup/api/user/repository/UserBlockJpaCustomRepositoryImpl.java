package com.pup.api.user.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class UserBlockJpaCustomRepositoryImpl implements UserBlockCustomRepository {
    private final JPAQueryFactory queryFactory;

}
