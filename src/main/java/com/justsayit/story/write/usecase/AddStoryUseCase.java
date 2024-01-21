package com.justsayit.story.write.usecase;

import com.justsayit.story.write.command.AddStoryCommand;

public interface AddStoryUseCase {

    void addStory(AddStoryCommand cmd);
}
