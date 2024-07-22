package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.QWalkingTrail;
import com.pup.api.walkingTrail.domain.QWalkingTrailLike;
import com.pup.api.walkingTrail.domain.QWalkingTrailReview;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV2;
import com.pup.global.enums.WalkingTrailSearchTypeEnum;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
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

    @Override
    public List<WalkingTrailV2> findWalkingTrailLikeByUserId(Integer userId, WalkingTrailSearchTypeEnum type) {
        QWalkingTrailReview wtr = QWalkingTrailReview.walkingTrailReview;
        QWalkingTrailLike wtl = QWalkingTrailLike.walkingTrailLike;

        JPAQuery<WalkingTrailV2> query = queryFactory.select(
                        Projections.constructor(WalkingTrailV2.class,
                                wtl.walkingTrailLikeId,
                                wtl.walkingTrail.mainImage,
                                wtl.walkingTrail.walkingTrailId,
                                wtl.walkingTrail.name,
                                wtl.walkingTrail.description,
                                wtl.walkingTrail.walkingTrailUid,
                                wtl.walkingTrail.time,
                                wtl.walkingTrail.distance,
                                wtl.walkingTrail.openRange,
                                wtl.walkingTrail.createdDate,
                                JPAExpressions
                                        .select(wtr.rating.avg())
                                        .from(wtr)
                                        .where(wtr.walkingTrail.walkingTrailId.eq(wtl.walkingTrail.walkingTrailId)),
                                wtl.user.userId,
                                JPAExpressions
                                        .select(wtr.count())
                                        .from(wtr)
                                        .where(wtr.walkingTrail.walkingTrailId.eq(wtl.walkingTrail.walkingTrailId)),
                                JPAExpressions
                                        .select(wtl.count())
                                        .from(wtl)
                                        .where(wtl.walkingTrail.walkingTrailId.eq(wtl.walkingTrail.walkingTrailId))
                        )
                ).from(wtl)
                .where(wtl.walkingTrail.isEnabled.eq(true)
                        .and(wtl.walkingTrail.isExposed.eq(true)
                                .and(wtl.walkingTrail.user.userId.ne(userId))));


        if (type.equals(WalkingTrailSearchTypeEnum.RECENT)) {
            query.orderBy(wtl.createdDate.desc());
        } else if (type.equals(WalkingTrailSearchTypeEnum.OLDEST)) {
            query.orderBy(wtl.createdDate.asc());
        }

        return query.fetch();
    }
}
