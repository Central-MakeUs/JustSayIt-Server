package com.justsayit.member.domain;

public enum LoginType {

    NAVER("naver"),
    APPLE("apple"),;

    private String type;

    LoginType(String type) {
        this.type = type;
    }
}
