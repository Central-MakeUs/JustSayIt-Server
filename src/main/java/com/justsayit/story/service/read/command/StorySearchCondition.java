package com.justsayit.story.service.read.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StorySearchCondition {

    private String sortBy;
    private String emotion;

    @Builder
    public StorySearchCondition(String sortBy, String emotion) {
        this.sortBy = sortBy;
        this.emotion = emotion;
    }
}
