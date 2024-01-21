package com.justsayit.story.service.read.usecase;

import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GetStoryUseCase {
    List<GetStoryRes> getMyStories(Long memberId, StorySearchCondition build, Pageable pageable);
}
