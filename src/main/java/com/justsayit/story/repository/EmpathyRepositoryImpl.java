package com.justsayit.story.repository;

import com.justsayit.story.domain.Empathy;
import com.justsayit.story.domain.EmpathyStatus;
import com.justsayit.story.repository.dto.EmpathyCountDto;
import com.justsayit.story.repository.dto.QEmpathyCountDto;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.justsayit.story.domain.QEmpathy.empathy;

public class EmpathyRepositoryImpl implements EmpathyRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public EmpathyRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<EmpathyCountDto> searchStoriesEmpathyCount(Long memberId, Long storyId) {
        return queryFactory.select(
                        new QEmpathyCountDto(
                                empathy.type.as("type"),
                                empathy.count().as("count")
                        )
                )
                .from(empathy)
                .where(
                        memberEq(memberId),
                        storyEq(storyId))
                .groupBy(empathy.type)
                .fetch();
    }

    @Override
    public Empathy searchValidEmpathy(Long memberId, Long storyId) {
        return queryFactory.selectFrom(empathy)
                .where(memberEq(memberId),
                        storyEq(storyId),
                        isValid())
                .fetchOne();
    }

    private BooleanExpression memberEq(Long memberId) {
        return empathy.member.id.eq(memberId);
    }

    private BooleanExpression storyEq(Long storyId) {
        return empathy.story.id.eq(storyId);
    }

    private BooleanExpression isValid() {
        return empathy.status.eq(EmpathyStatus.VALID);
    }
}
