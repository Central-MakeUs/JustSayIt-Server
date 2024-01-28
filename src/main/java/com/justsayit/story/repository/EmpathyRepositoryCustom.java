package com.justsayit.story.repository;

import com.justsayit.story.repository.dao.EmpathyCountDao;

import java.util.List;

public interface EmpathyRepositoryCustom {

    List<EmpathyCountDao> searchStoriesEmpathyCount(Long memberId, Long storyId);
}
