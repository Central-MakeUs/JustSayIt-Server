package com.justsayit.story.service.read.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StorySearchCondition {

    private Long storyId;
    private String emotion;
    private int size;

    @Builder
    public StorySearchCondition(Long storyId, String emotion, int size) {
        this.storyId = storyId;
        this.emotion = emotion;
        this.size = size;
    }
}
