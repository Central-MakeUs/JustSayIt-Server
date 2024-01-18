package com.justsayit.story.domain.feeling;

import com.justsayit.story.domain.Feeling;
import com.justsayit.story.domain.Story;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("HAPPINESS")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Happiness extends Feeling {

    public Happiness(Story story, boolean isSelected) {
        super(story, isSelected);
    }
}
