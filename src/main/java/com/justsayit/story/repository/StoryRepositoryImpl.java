package com.justsayit.story.repository;

import com.justsayit.story.repository.dao.StoryDao;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import javax.persistence.EntityManager;

public class StoryRepositoryImpl implements StoryRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public StoryRepositoryImpl(EntityManager em) {
        this.queryFactory = new JPAQueryFactory(em);
    }

    @Override
    public Page<StoryDao> searchMyStories(Long memberId, StorySearchCondition cond, Pageable pageable) {
        return null;
    }
}
