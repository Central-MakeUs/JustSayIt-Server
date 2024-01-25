package com.justsayit.story.repository;

import com.justsayit.story.domain.Feeling;
import com.justsayit.story.domain.Story;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
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
    public List<Story> searchMyStoriesOrderByLatest(Long memberId, StorySearchCondition cond, Pageable pageable) {
        return queryFactory.selectFrom(story)
                .where(memberIdEq(memberId),
                        isPosted(),
                        emotionEq(cond.getEmotion()))
                .orderBy(story.createdAt.desc())
                .offset(pageable.getOffset())
                .limit(pageable.getPageSize())
                .fetch();
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? story.memberId.eq(memberId) : null;
    }

    private BooleanExpression isPosted() {
        return story.metaInfo.deleted.eq(true);
    }

    private BooleanExpression emotionEq(String emotion) {
        return emotion != null ? story.mainContent.emotion.eq(Feeling.valueOf(emotion)) : null;
    }
}
