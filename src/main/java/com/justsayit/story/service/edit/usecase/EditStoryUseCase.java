package com.justsayit.story.service.edit.usecase;

import com.justsayit.story.service.edit.command.RemoveStoryCommand;

public interface EditStoryUseCase {

    void remove(RemoveStoryCommand cmd);
}
