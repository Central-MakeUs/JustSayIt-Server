package com.justsayit.story.domain;

import lombok.Getter;

import java.util.HashMap;
import java.util.Map;

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
        return BY_CODE.get(code);
    }

    private final String code;

    Feeling(String code) {
        this.code = code;
    }
}
