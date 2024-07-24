package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.QWalkingTrailReview;
import com.pup.api.walkingTrail.event.vo.WalkingTrailReviewV0;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class WalkingTrailReviewJpaCustomRepositoryImpl implements WalkingTrailReviewJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<WalkingTrailReviewV0> findWalkingTrailReviewList(Integer userId) {
        QWalkingTrailReview wtr = QWalkingTrailReview.walkingTrailReview;

        return queryFactory.select(Projections.constructor(
                        WalkingTrailReviewV0.class,
                        wtr.walkingTrailReviewId,
                        wtr.walkingTrail.mainImage,
                        wtr.walkingTrail.walkingTrailId,
                        wtr.walkingTrail.name,
                        wtr.walkingTrail.description,
                        wtr.walkingTrail.walkingTrailUid,
                        wtr.time,
                        wtr.walkingTrail.distance,
                        wtr.createdDate,
                        JPAExpressions
                                .select(wtr.rating.avg())
                                .from(wtr)
                                .where(wtr.walkingTrail.walkingTrailId.eq(wtr.walkingTrail.walkingTrailId)),
                        wtr.walkingTrail.user.userId,
                        wtr.walkingTrail.user.userUid,
                        JPAExpressions
                                .select(wtr.count())
                                .from(wtr)
                                .where(wtr.walkingTrail.walkingTrailId.eq(wtr.walkingTrail.walkingTrailId))))
                .from(wtr)
                .where(wtr.walkingTrail.user.userId.eq(userId))
                .fetch();
    }
}
