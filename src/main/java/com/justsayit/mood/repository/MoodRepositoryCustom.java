package com.justsayit.mood.repository;

import com.justsayit.mood.domain.Mood;

import java.util.List;

public interface MoodRepositoryCustom {

    List<Mood> searchTodayMoodOrderByOldest(Long memberId);

    Mood searchLatestMood(Long memberId);
}
