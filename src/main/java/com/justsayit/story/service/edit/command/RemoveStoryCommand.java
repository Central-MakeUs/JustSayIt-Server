package com.justsayit.story.service.edit.command;

import lombok.Getter;

@Getter
public class RemoveStoryCommand {

    private Long storyId;

    public RemoveStoryCommand(Long storyId) {
        this.storyId = storyId;
    }
}
