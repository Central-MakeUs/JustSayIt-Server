package com.justsayit.story.domain.feeling;

import com.justsayit.story.domain.Feeling;
import com.justsayit.story.domain.Story;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("ANGRY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Angry extends Feeling {

    public Angry(Story story, boolean isSelected) {
        super(story, isSelected);
    }
}
