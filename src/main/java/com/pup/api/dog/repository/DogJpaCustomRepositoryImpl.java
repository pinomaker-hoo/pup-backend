package com.pup.api.dog.repository;

import com.pup.api.dog.domain.QDog;
import com.pup.api.dog.event.vo.DogV0;
import com.querydsl.core.types.Projections;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class DogJpaCustomRepositoryImpl implements DogJpaCustomRepository {
    private final JPAQueryFactory queryFactory;

    @Override
    public List<DogV0> findDogListByUserId(Integer userId) {
        QDog d = QDog.dog;

        return queryFactory.select(
                        Projections.constructor(DogV0.class,
                                d.dogId,
                                d.name,
                                d.profile,
                                d.birth,
                                d.isNeutered
                        )).from(d)
                .where(d.user.userId.eq(userId))
                .fetch();
    }
}
