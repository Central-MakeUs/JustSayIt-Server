package com.justsayit.story.domain;

import com.justsayit.story.exception.InvalidEmotionCodeException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum Emotion {

    HAPPINESS("EMOTION001"),
    SADNESS("EMOTION002"),
    SURPRISED("EMOTION003"),
    ANGRY("EMOTION004"),
    ;


    private static final Map<String, Emotion> BY_CODE = new HashMap<>();

    static {
        for (Emotion e : values()) {
            BY_CODE.put(e.code, e);
        }
    }

    public static Emotion valueOfCode(String code) {
        return Optional.ofNullable(BY_CODE.get(code))
                .orElseThrow(InvalidEmotionCodeException::new);
    }

    private final String code;

    Emotion(String code) {
        this.code = code;
    }
}
