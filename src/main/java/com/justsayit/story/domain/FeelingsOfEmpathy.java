package com.justsayit.story.domain;

import com.justsayit.story.exception.InvalidFeelingCodeException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.util.List;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeelingsOfEmpathy {

    private boolean angrySelected;
    private boolean happinessSelected;
    private boolean sadnessSelected;
    private boolean surprisedSelected;

    @Builder
    private FeelingsOfEmpathy(boolean angrySelected, boolean happinessSelected, boolean sadnessSelected, boolean surprisedSelected) {
        this.angrySelected = angrySelected;
        this.happinessSelected = happinessSelected;
        this.sadnessSelected = sadnessSelected;
        this.surprisedSelected = surprisedSelected;
    }

    public static FeelingsOfEmpathy of(List<String> feelingsOfEmpathy) {
        boolean angrySelected = false;
        boolean happinessSelected = false;
        boolean sadnessSelected = false;
        boolean surprisedSelected = false;
        for (String s : feelingsOfEmpathy) {
            switch (Feeling.valueOfCode(s)) {
                case ANGRY:
                    angrySelected = true;
                    break;
                case HAPPINESS:
                    happinessSelected = true;
                    break;
                case SADNESS:
                    sadnessSelected = true;
                    break;
                case SURPRISED:
                    surprisedSelected = true;
                    break;
                default:
                    throw new InvalidFeelingCodeException();
            }
        }
        return FeelingsOfEmpathy.builder()
                .angrySelected(angrySelected)
                .happinessSelected(happinessSelected)
                .sadnessSelected(sadnessSelected)
                .surprisedSelected(surprisedSelected)
                .build();
    }
}
