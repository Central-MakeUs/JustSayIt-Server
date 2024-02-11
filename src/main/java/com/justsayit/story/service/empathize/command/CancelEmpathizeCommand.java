package com.justsayit.story.service.empathize.command;

import lombok.Getter;

@Getter
public class CancelEmpathizeCommand {

    private Long storyId;

    public CancelEmpathizeCommand(Long storyId) {
        this.storyId = storyId;
    }
}
