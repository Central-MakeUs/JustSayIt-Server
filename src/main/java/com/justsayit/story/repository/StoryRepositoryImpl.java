package com.justsayit.story.repository;

import com.justsayit.story.domain.Emotion;
import com.justsayit.story.domain.Story;
import com.justsayit.story.domain.StoryStatus;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;

import javax.persistence.EntityManager;
import java.util.List;

import static com.justsayit.story.domain.QStory.story;

public class StoryRepositoryImpl implements StoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public List<Story> searchMyPostedStoriesOrderByLatest(Long memberId, StorySearchCondition cond) {
        return queryFactory.selectFrom(story)
                .where(memberIdEq(memberId),
                        ltStoryId(cond.getStoryId()),
                        isPosted(),
                        emotionEq(cond.getEmotion()))
                .orderBy(story.id.desc())
                .limit(cond.getSize() + 1)
                .fetch();
    }

    @Override
    public List<Story> searchMyPostedStoriesOrderByOldest(Long memberId, StorySearchCondition cond) {
        return queryFactory.selectFrom(story)
                .where(memberIdEq(memberId),
                        gtStoryId(cond.getStoryId()),
                        isPosted(),
                        emotionEq(cond.getEmotion()))
                .orderBy(story.id.asc())
                .limit(cond.getSize() + 1)
                .fetch();
    }

    @Override
    public List<Story> searchAllPostedStoriesOrderByLatest(List<Long> blockedMemberList, StorySearchCondition cond) {
        return queryFactory.selectFrom(story)
                .where(ltStoryId(cond.getStoryId()),
                        isPosted(),
                        emotionEq(cond.getEmotion()),
                        isOpened(),
                        writerNotIn(blockedMemberList))
                .orderBy(story.id.desc())
                .limit(cond.getSize() + 1)
                .fetch();
    }

    private BooleanExpression isOpened() {
        return story.metaInfo.opened.isTrue();
    }

    private BooleanExpression writerNotIn(List<Long> blockedMemberList) {
        return blockedMemberList.isEmpty() ? null : story.memberId.notIn(blockedMemberList);
    }

    private BooleanExpression memberIdEq(Long memberId) {
        return memberId != null ? story.memberId.eq(memberId) : null;
    }

    private BooleanExpression isPosted() {
        return story.status.eq(StoryStatus.POSTED);
    }

    private BooleanExpression emotionEq(String emotion) {
        return emotion != null ? story.mainContent.emotion.eq(Emotion.valueOfCode(emotion)) : null;
    }

    private BooleanExpression ltStoryId(Long storyId) {
        return storyId != null ? story.id.lt(storyId) : null;
    }

    private BooleanExpression gtStoryId(Long storyId) {
        return storyId != null ? story.id.gt(storyId) : null;
    }
}
