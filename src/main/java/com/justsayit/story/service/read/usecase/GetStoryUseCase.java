package com.justsayit.story.service.read.usecase;

import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;

public interface GetStoryUseCase {

    GetStoryRes getMyStoriesOrderByLatest(StorySearchCondition cond);

    GetStoryRes getMyStoriesOrderByOldest(StorySearchCondition cond);

    GetStoryRes getAllStoriesOrderByLatest(StorySearchCondition cond);
}
