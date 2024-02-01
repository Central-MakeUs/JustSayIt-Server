package com.justsayit.story.service.empathize.command;

import lombok.Getter;

@Getter
public class CancelEmpathizeCommand {

    private Long memberId;
    private Long storyId;

    public CancelEmpathizeCommand(Long memberId, Long storyId) {
        this.memberId = memberId;
        this.storyId = storyId;
    }
}
