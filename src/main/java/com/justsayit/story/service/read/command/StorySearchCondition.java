package com.justsayit.story.service.read.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StorySearchCondition {

    private boolean latest;
    private boolean sortByPopularity;
    private String emotion;

    @Builder
    public StorySearchCondition(boolean latest, boolean sortByPopularity, String emotion) {
        this.latest = latest;
        this.sortByPopularity = sortByPopularity;
        this.emotion = emotion;
    }
}
