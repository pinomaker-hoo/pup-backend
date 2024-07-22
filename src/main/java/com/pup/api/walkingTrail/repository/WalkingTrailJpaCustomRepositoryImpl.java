package com.pup.api.walkingTrail.repository;

import com.pup.api.walkingTrail.domain.QWalkingTrail;
import com.pup.api.walkingTrail.domain.QWalkingTrailLike;
import com.pup.api.walkingTrail.domain.QWalkingTrailReview;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV0;
import com.pup.api.walkingTrail.event.vo.WalkingTrailV1;
import com.pup.global.enums.WalkingTrailSearchTypeEnum;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.JPAExpressions;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;
import java.util.UUID;

import static com.pup.global.enums.WalkingTrailSearchTypeEnum.OLDEST;
import static com.pup.global.enums.WalkingTrailSearchTypeEnum.RECENT;

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
                                wt.mainImage,
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

    @Override
    public List<WalkingTrailV1> findAllByWord(String word, Integer userId, WalkingTrailSearchTypeEnum searchType) {
        QWalkingTrail wt = QWalkingTrail.walkingTrail;
        QWalkingTrailReview wtr = QWalkingTrailReview.walkingTrailReview;
        QWalkingTrailLike wtl = QWalkingTrailLike.walkingTrailLike;

        JPAQuery<WalkingTrailV1> query = queryFactory.select(
                        Projections.constructor(WalkingTrailV1.class,
                                wt.walkingTrailId,
                                wt.mainImage,
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
                                        .where(wtr.walkingTrail.walkingTrailId.eq(wt.walkingTrailId)),
                                wt.user.userId,
                                wt.user.userUid,
                                JPAExpressions
                                        .select(wtr.count())
                                        .from(wtr)
                                        .where(wtr.walkingTrail.walkingTrailId.eq(wt.walkingTrailId)),
                                JPAExpressions
                                        .select(wtl.count())
                                        .from(wtl)
                                        .where(wtl.walkingTrail.walkingTrailId.eq(wt.walkingTrailId))
                        ))
                .from(wt)
                .where(wt.isEnabled.eq(true).and(wt.isExposed.eq(true).and(wt.user.userId.ne(userId))));

        if (word != null) {
            query.where(wt.name.contains(word));
        }

        if (searchType == RECENT) {
            query.orderBy(wt.createdDate.desc());
        }

        if (searchType == OLDEST) {
            query.orderBy(wt.createdDate.asc());
        }

        return query.fetch();
    }

    @Override
    public WalkingTrailV1 findByWalkingTrailUid(UUID walkingTrailUid) {
        QWalkingTrail wt = QWalkingTrail.walkingTrail;
        QWalkingTrailReview wtr = QWalkingTrailReview.walkingTrailReview;
        QWalkingTrailLike wtl = QWalkingTrailLike.walkingTrailLike;

        return queryFactory.select(
                        Projections.constructor(WalkingTrailV1.class,
                                wt.walkingTrailId,
                                wt.mainImage,
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
                                        .where(wtr.walkingTrail.walkingTrailId.eq(wt.walkingTrailId)),
                                wt.user.userId,
                                JPAExpressions
                                        .select(wtr.count())
                                        .from(wtr)
                                        .where(wtr.walkingTrail.walkingTrailId.eq(wt.walkingTrailId)),
                                JPAExpressions
                                        .select(wtl.count())
                                        .from(wtl)
                                        .where(wtl.walkingTrail.walkingTrailId.eq(wt.walkingTrailId))
                        ))
                .from(wt)
                .where(wt.walkingTrailUid.eq(walkingTrailUid))
                .fetchOne();
    }
}
