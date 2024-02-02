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
    private static final Map<Emotion, String> BY_VALUE = new HashMap<>();

    static {
        for (Emotion e : values()) {
            BY_CODE.put(e.code, e);
        }
        for (Emotion e : values()) {
            BY_VALUE.put(e, e.code);
        }
    }

    public static Emotion valueOfCode(String code) {
        return Optional.ofNullable(BY_CODE.get(code))
                .orElseThrow(InvalidEmotionCodeException::new);
    }

    public static String codeOfValue(Emotion value) {
        return Optional.ofNullable(BY_VALUE.get(value))
                .orElse(null);
    }

    private final String code;

    Emotion(String code) {
        this.code = code;
    }
}
