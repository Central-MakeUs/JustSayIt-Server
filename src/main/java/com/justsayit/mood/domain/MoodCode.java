package com.justsayit.mood.domain;

import com.justsayit.mood.exception.InvalidMoodCodeException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum MoodCode {

    HAPPINESS("MOOD001"),
    SADNESS("MOOD002"),
    SURPRISED("MOOD003"),
    ANGRY("MOOD004"),
    ;

    private static final Map<String, MoodCode> BY_CODE = new HashMap<>();

    static {
        for (MoodCode moodCode : values()) {
            BY_CODE.put(moodCode.getCode(), moodCode);
        }
    }

    public static MoodCode valueOfCode(String code) {
        return Optional.ofNullable(BY_CODE.get(code))
                .orElseThrow(InvalidMoodCodeException::new);
    }

    private final String code;

    MoodCode(String code) {
        this.code = code;
    }
}
