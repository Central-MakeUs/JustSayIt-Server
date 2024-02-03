package com.justsayit.mood.service.command;

import lombok.Getter;

@Getter
public class SaveMoodCommand {

    private String moodCode;

    public SaveMoodCommand(String moodCode) {
        this.moodCode = moodCode;
    }
}
