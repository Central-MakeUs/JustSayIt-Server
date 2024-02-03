package com.justsayit.mood.service.usecase;

import com.justsayit.mood.service.command.SaveMoodCommand;

public interface SaveMoodUseCase {

    void saveMood(SaveMoodCommand cmd);
}
