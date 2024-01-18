package com.justsayit.story.domain.feeling;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Angry extends Feeling {

    public Angry(int count, boolean selected) {
        super(count, selected);
    }
}
