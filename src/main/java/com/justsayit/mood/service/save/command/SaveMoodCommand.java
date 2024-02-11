package com.justsayit.mood.service.save.command;

import lombok.Getter;

@Getter
public class SaveMoodCommand {

    private String moodCode;

    public SaveMoodCommand(String moodCode) {
        this.moodCode = moodCode;
    }
}
