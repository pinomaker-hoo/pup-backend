package com.pup.api.user.repository;

import com.pup.api.dog.domain.QDog;
import com.pup.api.user.event.vo.UserV0;
import com.pup.api.user.event.vo.UserV1;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import com.pup.api.user.domain.QUser;
import com.pup.api.user.event.vo.LoginUser;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
                                u.userUid,
                                u.profile,
                                u.description
                        )
                ).from(u)
                .where(u.userId.eq(userId))
                .fetchOne();
    }

    @Override
    public List<UserV1> findUserList(Integer userId) {
        QUser u = QUser.user;
        QDog d = QDog.dog;

        JPAQuery<Tuple> query = queryFactory.select(
                        u.userId,
                        u.userUid,
                        u.profile,
                        d.profile
                ).from(u)
                .leftJoin(u.dogList, d)
                .where(u.userId.ne(userId));

        List<Tuple> results = query.fetch();

        Map<Integer, UserV1> friendMap = new HashMap<>();

        results.forEach(tuple -> {
            Integer uId = tuple.get(u.userId);
            UserV1 user = friendMap.computeIfAbsent(uId, id -> new UserV1(
                    id,
                    tuple.get(u.userUid),
                    tuple.get(u.profile),
                    new ArrayList<>()
            ));

            String dogProfile = tuple.get(d.profile);
            if (dogProfile != null) {
                user.getDogProfileList().add(dogProfile);
            }
        });

        return new ArrayList<>(friendMap.values());
    }
}
