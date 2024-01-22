package com.justsayit.story.repository;

import com.justsayit.story.domain.Story;
import com.justsayit.story.domain.StoryStatus;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;
import java.util.List;

import static com.justsayit.story.domain.QStory.story;

public class StoryRepositoryImpl implements StoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Story> searchMyStories(Long memberId, StorySearchCondition cond, Pageable pageable) {
        return queryFactory.selectFrom(story)
                .where(story.memberId.eq(memberId).and(story.status.eq(StoryStatus.POSTED)))
                .orderBy(story.createdAt.asc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }
}
