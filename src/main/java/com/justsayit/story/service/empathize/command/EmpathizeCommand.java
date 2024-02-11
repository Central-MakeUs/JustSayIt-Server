package com.justsayit.story.service.empathize.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmpathizeCommand {

    private Long storyId;
    private String emotionCode;

    @Builder
    public EmpathizeCommand(Long storyId, String emotionCode) {
        this.storyId = storyId;
        this.emotionCode = emotionCode;
    }
}
