package com.justsayit.story.domain;

import com.justsayit.story.exception.InvlaidBodyTextLengthException;
import lombok.AccessLevel;
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
    private Feeling feeling;
    private String bodyText;

    private MainContent(Feeling feeling, String bodyText) {
        validateBodyTextLength(bodyText);
        this.feeling = feeling;
        this.bodyText = bodyText;
    }

    public static MainContent of(String emotionCode, String bodyText) {
        return new MainContent(Feeling.valueOfCode(emotionCode), bodyText);
    }

    private void validateBodyTextLength(String bodyText) {
        if (bodyText.length() > 300) {
            throw new InvlaidBodyTextLengthException();
        }
    }
}
