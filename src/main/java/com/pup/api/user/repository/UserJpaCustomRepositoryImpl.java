package com.pup.api.user.repository;

import com.pup.api.user.event.vo.UserV0;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.pup.api.user.domain.QUser;
import com.pup.api.user.event.vo.LoginUser;

@RequiredArgsConstructor
public class UserJpaCustomRepositoryImpl implements UserJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public boolean existedUserByEmail(String email) {
        QUser u = QUser.user;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(u)
                .where(u.email.eq(email))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public LoginUser findUserByEmail(String email) {
        QUser u = QUser.user;

        return queryFactory.select(
                        Projections.constructor(
                                LoginUser.class,
                                u.userId,
                                u.email,
                                u.password,
                                u.nickname,
                                u.userUid)
                ).from(u)
                .where(u.email.eq(email))
                .fetchOne();
    }

    @Override
    public UserV0 findUserByUserId(Integer userId) {
        QUser u = QUser.user;

        return queryFactory.select(
                        Projections.constructor(
                                UserV0.class,
                                u.userId,
                                u.email,
                                u.nickname,
                                u.userUid,
                                u.profile
                        )
                ).from(u)
                .where(u.userId.eq(userId))
                .fetchOne();
    }
}
