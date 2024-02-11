package com.justsayit.mood.service.save.usecase;

import com.justsayit.mood.service.save.command.SaveMoodCommand;

public interface SaveMoodUseCase {

    void saveMood(SaveMoodCommand cmd);
}
