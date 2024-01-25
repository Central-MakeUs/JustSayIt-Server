package com.justsayit.story.service.read.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StorySearchCondition {

    private Long storyId;
    private Boolean latest;
    private Boolean popular;
    private String emotion;
    private int size;

    @Builder
    public StorySearchCondition(Long storyId, Boolean latest, Boolean popular, String emotion, int size) {
        this.storyId = storyId;
        this.latest = latest;
        this.popular = popular;
        this.emotion = emotion;
        this.size = size;
    }
}
