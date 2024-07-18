package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.QWalkingTrail;
import com.pup.api.walkingTrail.domain.QWalkingTrailReview;
import com.pup.api.walkingTrail.domain.WalkingTrail;
import com.pup.api.walkingTrail.domain.WalkingTrailReview;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV0;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WalkingTrailJpaCustomRepositoryImpl implements WalkingTrailJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<WalkingTrailV0> findAllByUserId(Integer userId) {
        QWalkingTrail wt = QWalkingTrail.walkingTrail;
        QWalkingTrailReview wtr = QWalkingTrailReview.walkingTrailReview;

        return queryFactory.select(
                        Projections.constructor(WalkingTrailV0.class,
                                wt.walkingTrailId,
                                wt.name,
                                wt.description,
                                wt.walkingTrailUid,
                                wt.time,
                                wt.distance,
                                wt.openRange,
                                wt.createdDate,
                                JPAExpressions
                                        .select(wtr.rating.avg())
                                        .from(wtr)
                                        .where(wtr.walkingTrail.walkingTrailId.eq(wt.walkingTrailId))
                        ))
                .from(wt)
                .leftJoin(wt.walkingTrailReviewList, wtr)
                .where(wt.user.userId.eq(userId))
                .fetch();
    }
}
