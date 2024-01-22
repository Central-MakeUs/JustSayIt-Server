package com.justsayit.story.service.read.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class StorySearchCondition {

    private Boolean latest;
    private Boolean popular;
    private String emotion;

    @Builder
    public StorySearchCondition(Boolean latest, Boolean popular, String emotion) {
        this.latest = latest;
        this.popular = popular;
        this.emotion = emotion;
    }
}
