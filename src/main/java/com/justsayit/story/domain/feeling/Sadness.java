package com.justsayit.story.domain.feeling;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Sadness {

    @Transient
    private static final int ZERO = 0;
    private int count;
    private boolean selected;

    public Sadness(boolean selected) {
        this.count = ZERO;
        this.selected = selected;
    }
}
