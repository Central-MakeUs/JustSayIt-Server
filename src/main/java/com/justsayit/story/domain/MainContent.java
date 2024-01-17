package com.justsayit.story.domain;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainContent {

    private String title;
    @Enumerated(value = EnumType.STRING)
    private Emotion emotion;
    private String bodyText;

    @Builder
    public MainContent(String title, Emotion emotion, String bodyText) {
        this.title = title;
        this.emotion = emotion;
        this.bodyText = bodyText;
    }
}
