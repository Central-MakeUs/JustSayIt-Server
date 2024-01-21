package com.justsayit.story.domain;

import lombok.AccessLevel;
import lombok.Builder;
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

    @Builder
    public MetaInfo(boolean opened, boolean anonymous, boolean deleted) {
        this.opened = opened;
        this.anonymous = anonymous;
        this.deleted = deleted;
    }
}
