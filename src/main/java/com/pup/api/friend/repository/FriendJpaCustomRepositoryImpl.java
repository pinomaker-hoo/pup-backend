package com.pup.api.friend.repository;


import com.pup.api.friend.domain.QFriend;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

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
}
