package com.justsayit.story.domain;

import com.justsayit.story.exception.InvlaidBodyTextLengthException;
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

    @Enumerated(value = EnumType.STRING)
    private Emotion emotion;
    private String bodyText;

    @Builder
    public MainContent(Emotion emotion, String bodyText) {
        validateBodyTextLength(bodyText);
        this.emotion = emotion;
        this.bodyText = bodyText;
    }

    private void validateBodyTextLength(String bodyText) {
        if (bodyText.length() > 300) {
            throw new InvlaidBodyTextLengthException();
        }
    }
}
