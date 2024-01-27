package com.justsayit.story.repository;

import com.justsayit.story.repository.dao.EmpathyCountDao;
import com.justsayit.story.repository.dao.QEmpathyCountDao;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.justsayit.story.domain.QEmpathyCount.empathyCount;

public class EmpathyCountRepositoryImpl implements EmpathyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public EmpathyCountRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<EmpathyCountDao> searchMyPostedStoriesEmpathyCount(Long memberId, Long storyId) {
        return queryFactory.select(
                        new QEmpathyCountDao(
                                empathyCount.type.as("type"),
                                empathyCount.count().as("count")
                        )
                )
                .from(empathyCount)
                .where(
                        memberEq(memberId),
                        storyEq(storyId))
                .groupBy(empathyCount.type)
                .fetch();
    }

    private BooleanExpression memberEq(Long memberId) {
        return empathyCount.member.id.eq(memberId);
    }

    private BooleanExpression storyEq(Long storyId) {
        return empathyCount.story.id.eq(storyId);
    }
}
