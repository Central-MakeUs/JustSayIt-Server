package com.justsayit.story.domain.feeling;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Happiness extends Feeling {

    public Happiness(int count, boolean selected) {
        super(count, selected);
    }
}
