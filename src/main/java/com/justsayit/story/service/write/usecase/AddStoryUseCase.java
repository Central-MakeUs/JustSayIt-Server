package com.justsayit.story.service.write.usecase;

import com.justsayit.story.service.write.command.AddStoryCommand;

public interface AddStoryUseCase {

    void addStory(AddStoryCommand cmd);
}
