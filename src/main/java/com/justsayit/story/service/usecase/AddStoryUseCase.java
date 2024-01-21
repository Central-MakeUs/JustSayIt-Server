package com.justsayit.story.service.usecase;

import com.justsayit.story.service.command.AddStoryCommand;

public interface AddStoryUseCase {

    void addStory(AddStoryCommand cmd);
}
