package com.justsayit.story.service.read.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StorySearchCondition {

    private Long storyId;
    private Boolean popularity;
    private String emotion;
    private int size;

    @Builder
    public StorySearchCondition(Long storyId, Boolean popularity, String emotion, int size) {
        this.storyId = storyId;
        this.popularity = popularity;
        this.emotion = emotion;
        this.size = size;
    }
}
