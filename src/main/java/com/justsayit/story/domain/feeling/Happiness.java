package com.justsayit.story.domain.feeling;

import com.justsayit.story.domain.Feeling;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HAPPINESS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Happiness extends Feeling {

    public Happiness(boolean isSelected) {
        super(isSelected);
    }
}
