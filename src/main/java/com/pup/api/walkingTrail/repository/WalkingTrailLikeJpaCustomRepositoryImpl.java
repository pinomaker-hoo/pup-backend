package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.QWalkingTrailLike;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.UUID;

@RequiredArgsConstructor
public class WalkingTrailLikeJpaCustomRepositoryImpl implements WalkingTrailLikeJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public Boolean existByUserAndWalkingTrail(Integer userId, UUID walkingTrailUid) {
        QWalkingTrailLike wtl = QWalkingTrailLike.walkingTrailLike;

        Integer walkingTrailLike = queryFactory.selectOne()
                .from(wtl)
                .where(wtl.user.userId.eq(userId).and(wtl.walkingTrail.walkingTrailUid.eq(walkingTrailUid)))
                .fetchFirst();

        return walkingTrailLike != null;
    }
}
