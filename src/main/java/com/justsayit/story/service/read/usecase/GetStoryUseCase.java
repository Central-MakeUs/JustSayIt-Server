package com.justsayit.story.service.read.usecase;

import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;

public interface GetStoryUseCase {

    GetStoryRes getMyPostedStoriesOrderByLatest(Long memberId, StorySearchCondition cond);
}
