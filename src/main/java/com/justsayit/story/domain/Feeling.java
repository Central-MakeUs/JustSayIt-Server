package com.justsayit.story.domain;

import com.justsayit.story.exception.InvalidEmotionCodeException;
import lombok.Getter;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Getter
public enum Feeling {

    HAPPINESS("FEELING001"),
    SADNESS("FEELING002"),
    SURPRISED("FEELING003"),
    ANGRY("FEELING004"),
    ;


    private static final Map<String, Feeling> BY_CODE = new HashMap<>();

    static {
        for (Feeling f : values()) {
            BY_CODE.put(f.code, f);
        }
    }

    public static Feeling valueOfCode(String code) {
        return Optional.ofNullable(BY_CODE.get(code))
                .orElseThrow(InvalidEmotionCodeException::new);
    }

    private final String code;

    Feeling(String code) {
        this.code = code;
    }
}
