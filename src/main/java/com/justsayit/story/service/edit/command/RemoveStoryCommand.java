package com.justsayit.story.service.edit.command;

import lombok.Getter;

@Getter
public class RemoveStoryCommand {

    private Long memberId;
    private Long storyId;

    public RemoveStoryCommand(Long memberId, Long storyId) {
        this.memberId = memberId;
        this.storyId = storyId;
    }
}
