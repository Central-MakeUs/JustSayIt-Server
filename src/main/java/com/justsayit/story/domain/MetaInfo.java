package com.justsayit.story.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class MetaInfo {

    private boolean opened;
    private boolean anonymous;
    private boolean modified;

    private MetaInfo(boolean opened, boolean anonymous, boolean modified) {
        this.opened = opened;
        this.anonymous = anonymous;
        this.modified = modified;
    }

    public static MetaInfo ofNew(boolean opened, boolean anonymous) {
        return new MetaInfo(opened, anonymous, false);
    }

    public static MetaInfo ofModified(boolean opened, boolean anonymous) {
        return new MetaInfo(opened, anonymous, true);
    }
}
