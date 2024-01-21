package com.justsayit.story.repository;

import com.justsayit.story.repository.dao.StoryDao;
import com.justsayit.story.service.read.command.StorySearchCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

public interface StoryRepositoryCustom {

    Page<StoryDao> searchMyStories(Long memberId, StorySearchCondition cond, Pageable pageable);
}
