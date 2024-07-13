package com.pup.api.friend.repository;

import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class FriendJpaCustomRepositoryImpl implements FriendJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

}
