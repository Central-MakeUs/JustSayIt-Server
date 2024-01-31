package com.justsayit.story.repository;

import com.justsayit.story.domain.Empathy;
import com.justsayit.story.repository.dto.EmpathyCountDto;

import java.util.List;

public interface EmpathyRepositoryCustom {

    List<EmpathyCountDto> searchStoriesEmpathyCount(Long memberId, Long storyId);

    Empathy searchValidEmpathy(Long memberId, Long storyId);
}
