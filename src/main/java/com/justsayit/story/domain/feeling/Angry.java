package com.justsayit.story.domain.feeling;

import com.justsayit.story.domain.Feeling;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ANGRY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Angry extends Feeling {

    public Angry(boolean isSelected) {
        super(isSelected);
    }
}
