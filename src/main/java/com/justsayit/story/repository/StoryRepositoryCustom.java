package com.justsayit.story.repository;

import com.justsayit.story.domain.Story;
import com.justsayit.story.service.read.command.StorySearchCondition;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface StoryRepositoryCustom {

    List<Story> searchMyStoriesOrderByLatest(Long memberId, StorySearchCondition cond, Pageable pageable);
}
