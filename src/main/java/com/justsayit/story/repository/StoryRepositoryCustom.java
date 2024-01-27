package com.justsayit.story.repository;

import com.justsayit.story.domain.Story;
import com.justsayit.story.service.read.command.StorySearchCondition;

import java.util.List;

public interface StoryRepositoryCustom {

    List<Story> searchMyPostedStoriesOrderByLatest(Long memberId, StorySearchCondition cond);

    List<Story> searchMyPostedStoriesOrderByOldest(Long memberId, StorySearchCondition cond);
}
