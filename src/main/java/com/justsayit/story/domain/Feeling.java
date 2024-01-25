package com.justsayit.story.domain;

import lombok.Getter;

import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Getter
public enum Feeling {

    HAPPINESS("FEELING001", "HAPPINESS"),
    SADNESS("FEELING002", "SADNESS"),
    SURPRISED("FEELING003", "SURPRISED"),
    ANGRY("FEELING004", "ANGRY"),
    ;

    private String code;
    private String desc;

    Feeling(String code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    private static final Map<String, Feeling> BY_CODE = Stream.of(values()).collect(Collectors.toMap(Feeling::getCode, Function.identity()));
    private static final Map<String, Feeling> BY_VALUE = Stream.of(values()).collect(Collectors.toMap(Feeling::getDesc, Function.identity()));

    public static Feeling valueOfCode(String code) {
        return BY_CODE.get(code);
    }

    public static Feeling valueOfDesc(String desc) {
        return BY_CODE.get(desc);
    }
}
