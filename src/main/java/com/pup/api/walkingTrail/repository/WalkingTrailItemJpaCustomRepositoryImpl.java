package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.QWalkingTrailItem;
import com.pup.api.walkingTrail.event.vo.WalkingTrailItemV0;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WalkingTrailItemJpaCustomRepositoryImpl implements WalkingTrailItemJpaCustomRepository {
    private final JPAQueryFactory queryFactory;


    @Override
    public List<WalkingTrailItemV0> findWalkingTrailItemV0ByWalkingTrailId(Long walkingTrailId) {
        QWalkingTrailItem wti = QWalkingTrailItem.walkingTrailItem;

        return queryFactory.select(
                        Projections.constructor(WalkingTrailItemV0.class,
                                wti.walkingTrailItemId,
                                wti.lat,
                                wti.lng
                        )).from(wti)
                .where(wti.walkingTrail.walkingTrailId.eq(walkingTrailId))
                .orderBy(wti.createdDate.asc())
                .fetch();
    }
}
