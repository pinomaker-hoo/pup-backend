package com.pup.api.friend.repository;


import com.pup.api.dog.domain.QDog;
import com.pup.api.friend.domain.QFriend;
import com.pup.api.friend.event.vo.FriendV0;
import com.pup.api.friend.event.vo.FriendV1;
import com.querydsl.core.Tuple;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RequiredArgsConstructor
public class FriendJpaCustomRepositoryImpl implements FriendJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existedFriend(Integer userId, Integer targetUserId) {
        QFriend f = QFriend.friend1;

        Integer fetchOne = queryFactory
                .selectOne()
                .from(f)
                .where(f.user.userId.eq(userId).and(f.friend.userId.eq(targetUserId)))
                .fetchFirst();

        return fetchOne != null;
    }

    @Override
    public List<FriendV0> findFriendList(Integer userId, String name) {
        QFriend f = QFriend.friend1;
        QDog d = QDog.dog;

        JPAQuery<Tuple> query = queryFactory
                .select(
                        f.friendId,
                        f.friend.userId,
                        f.friend.userUid,
                        f.friend.description,
                        f.friend.profile,
                        f.friend.lastWakingDate,
                        d.profile
                )
                .from(f)
                .leftJoin(f.friend.dogList, d)
                .where(f.user.userId.eq(userId));

        List<Tuple> results = query.fetch();

        Map<Long, FriendV0> friendMap = new HashMap<>();

        results.forEach(tuple -> {
            Long friendId = tuple.get(f.friendId);
            FriendV0 friend = friendMap.computeIfAbsent(friendId, id -> new FriendV0(
                    id,
                    tuple.get(f.friend.userId),
                    tuple.get(f.friend.userUid),
                    tuple.get(f.friend.description),
                    tuple.get(f.friend.profile),
                    tuple.get(f.friend.lastWakingDate),
                    new ArrayList<>()
            ));

            String dogProfile = tuple.get(d.profile);
            if (dogProfile != null) {
                friend.getDogProfileList().add(dogProfile);
            }
        });

        return new ArrayList<>(friendMap.values());
    }

    @Override
    public List<FriendV1> findFriendListV1(Integer userId) {
        QFriend f = QFriend.friend1;

        return queryFactory.select(
                        Projections.constructor(
                                FriendV1.class,
                                f.friendId,
                                f.user.userId,
                                f.friend.userId
                        ))
                .from(f)
                .where(f.user.userId.eq(userId))
                .fetch();
    }
}
