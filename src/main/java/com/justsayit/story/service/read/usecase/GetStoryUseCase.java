package com.justsayit.story.service.read.usecase;

import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;

public interface GetStoryUseCase {

    GetStoryRes getMyStoriesOrderByLatest(Long memberId, StorySearchCondition cond);

    GetStoryRes getMyStoriesOrderByOldest(Long memberId, StorySearchCondition cond);

    GetStoryRes getAllStoriesOrderByLatest(Long memberId, StorySearchCondition cond);
}
