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
    private boolean deleted;
    private boolean modified;

    private MetaInfo(boolean opened, boolean anonymous, boolean deleted, boolean modified) {
        this.opened = opened;
        this.anonymous = anonymous;
        this.deleted = deleted;
        this.modified = modified;
    }

    public static MetaInfo newMetaInfo(boolean opened, boolean anonymous) {
        return new MetaInfo(opened, anonymous, false, false);
    }

    public static MetaInfo deletedMetaInfo(boolean opened, boolean anonymous) {
        return new MetaInfo(opened, anonymous, true, false);
    }

    public static MetaInfo modifiedMetaInfo(boolean opened, boolean anonymous) {
        return new MetaInfo(opened, anonymous, false, true);
    }
}
