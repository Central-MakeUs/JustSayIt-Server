package com.justsayit.story.domain.feeling;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sadness extends Feeling {

    public Sadness(int count, boolean selected) {
        super(count, selected);
    }
}
