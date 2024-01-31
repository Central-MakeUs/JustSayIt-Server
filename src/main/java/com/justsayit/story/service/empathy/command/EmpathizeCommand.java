package com.justsayit.story.service.empathy.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class EmpathizeCommand {

    private Long memberId;
    private Long storyId;
    private String emotionCode;

    @Builder
    public EmpathizeCommand(Long memberId, Long storyId, String emotionCode) {
        this.memberId = memberId;
        this.storyId = storyId;
        this.emotionCode = emotionCode;
    }
}
