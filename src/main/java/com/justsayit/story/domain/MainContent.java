package com.justsayit.story.domain;

import com.justsayit.story.exception.InvalidBodyTextException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Transient;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MainContent {

    @Transient
    private final int MAX_BODY_TEXT_LENGTH = 300;
    @Transient
    private final int MIN_BODY_TEXT_LENGTH = 0;
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
        if (isNull(bodyText) || isLongerThanMaxLength(bodyText) || isAllBlankSpace(bodyText)) {
            throw new InvalidBodyTextException();
        }
    }

    private boolean isNull(String bodyText) {
        return bodyText == null;
    }

    private boolean isLongerThanMaxLength(String bodyText) {
        return bodyText.length() > MAX_BODY_TEXT_LENGTH;
    }

    private boolean isAllBlankSpace(String bodyText) {
        return bodyText.trim().length() == MIN_BODY_TEXT_LENGTH;
    }
}
