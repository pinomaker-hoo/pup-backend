package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.QWalkingTrailImage;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WalkingTrailImageJpaCustomRepositoryImpl implements WalkingTrailImageJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<String> findWalkingTrailImageByWalkingTrailId(Long walkingTrailId) {
        QWalkingTrailImage wti = QWalkingTrailImage.walkingTrailImage;

        return queryFactory.select(
                        Projections.constructor(
                                String.class,
                                wti.path
                        ))
                .from(wti)
                .where(wti.walkingTrail.walkingTrailId.eq(walkingTrailId))
                .fetch();
    }

}
