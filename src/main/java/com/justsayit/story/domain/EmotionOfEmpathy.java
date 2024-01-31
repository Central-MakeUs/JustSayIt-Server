package com.justsayit.story.domain;

import com.justsayit.story.exception.InvalidEmotionCodeException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmotionOfEmpathy {

    private boolean angrySelected;
    private boolean happinessSelected;
    private boolean sadnessSelected;
    private boolean surprisedSelected;

    @Builder
    private EmotionOfEmpathy(boolean angrySelected, boolean happinessSelected, boolean sadnessSelected, boolean surprisedSelected) {
        this.angrySelected = angrySelected;
        this.happinessSelected = happinessSelected;
        this.sadnessSelected = sadnessSelected;
        this.surprisedSelected = surprisedSelected;
    }

    public static EmotionOfEmpathy of(List<String> emotionList) {
        boolean angrySelected = false;
        boolean happinessSelected = false;
        boolean sadnessSelected = false;
        boolean surprisedSelected = false;
        for (String emotion : emotionList) {
            switch (emotion) {
                case "EMOTION001":
                    happinessSelected = true;
                    break;
                case "EMOTION002":
                    sadnessSelected = true;
                    break;
                case "EMOTION003":
                    surprisedSelected = true;
                    break;
                case "EMOTION004":
                    angrySelected = true;
                    break;
                default:
                    throw new InvalidEmotionCodeException();
            }
        }
        return EmotionOfEmpathy.builder()
                .angrySelected(angrySelected)
                .happinessSelected(happinessSelected)
                .sadnessSelected(sadnessSelected)
                .surprisedSelected(surprisedSelected)
                .build();
    }
}
