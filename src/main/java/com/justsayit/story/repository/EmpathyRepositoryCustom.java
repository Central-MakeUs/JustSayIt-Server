package com.justsayit.story.repository;

import com.justsayit.story.repository.dao.EmpathyCountDao;

import java.util.List;

public interface EmpathyRepositoryCustom {

    List<EmpathyCountDao> searchMyPostedStoriesEmpathyCount(Long memberId, Long storyId);
}
