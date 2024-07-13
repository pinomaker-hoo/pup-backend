package com.pup.api.user.repository;

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
    public LoginUser findUserByUsername(String username) {
        QUser u = QUser.user;

        return queryFactory.select(
                        Projections.constructor(
                                LoginUser.class,
                                u.userId,
                                u.nickname,
                                u.password
                        )
                ).from(u)
                .where(u.nickname.eq(username)).fetchOne();
    }
}
