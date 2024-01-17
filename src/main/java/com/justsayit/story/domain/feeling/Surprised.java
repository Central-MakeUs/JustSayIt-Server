package com.justsayit.story.domain.feeling;

import com.justsayit.story.domain.Feeling;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;


@Entity
@DiscriminatorValue("SURPRISED")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Surprised extends Feeling {

    public Surprised(boolean isSelected) {
        super(isSelected);
    }
}
