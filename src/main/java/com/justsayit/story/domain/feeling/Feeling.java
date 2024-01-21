package com.justsayit.story.domain.feeling;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Embeddable;
import javax.persistence.Transient;

@Embeddable
@Access(AccessType.FIELD)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Feeling {

    @Transient
    private static final int ZERO = 0;
    private int count;
    private boolean selected;

    protected Feeling(int count, boolean selected) {
        this.count = count;
        this.selected = selected;
    }

    public int getCount() {
        return selected ? this.count : ZERO;
    }

    public boolean isSelected() {
        return selected;
    }
}
