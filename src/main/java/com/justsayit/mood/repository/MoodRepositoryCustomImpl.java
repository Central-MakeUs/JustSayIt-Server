package com.justsayit.mood.repository;

import com.justsayit.mood.domain.Mood;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

import static com.justsayit.mood.domain.QMood.mood;

public class MoodRepositoryCustomImpl implements MoodRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public MoodRepositoryCustomImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    private final int ONE = 1;

    @Override
    public List<Mood> searchTodayMoodOrderByOldest(Long memberId) {
        LocalDateTime today = LocalDateTime.now();
        return queryFactory.selectFrom(mood)
                .where(
                        memberEq(memberId),
                        mood.createdAt.between(
                                today.toLocalDate().atStartOfDay(),
                                today.toLocalDate().atTime(LocalTime.MAX)
                        )
                )
                .orderBy(mood.createdAt.asc())
                .fetch();
    }

    @Override
    public Mood searchLatestMood(Long memberId) {
        return queryFactory.selectFrom(mood)
                .where(
                        memberEq(memberId)
                )
                .orderBy(mood.createdAt.desc())
                .limit(ONE)
                .fetchOne();

    }

    private BooleanExpression memberEq(Long memberId) {
        return mood.member.id.eq(memberId);
    }
}
