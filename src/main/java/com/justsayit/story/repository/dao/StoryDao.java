package com.justsayit.story.repository.dao;

import com.justsayit.story.domain.FeelingsOfEmpathy;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class StoryDao {

    private Long storyId;
    private Long writerId;
    private String uuid;
    private String emotion;
    private String bodyText;
    private boolean opened;
    private boolean anonymous;
    private boolean deleted;
    private boolean modified;
    private FeelingsOfEmpathy feelingsOfEmpathy;

    @QueryProjection
    public StoryDao(Long storyId, Long writerId, String uuid, String emotion, String bodyText, boolean opened, boolean anonymous, boolean deleted, boolean modified, FeelingsOfEmpathy feelingsOfEmpathy) {
        this.storyId = storyId;
        this.writerId = writerId;
        this.uuid = uuid;
        this.emotion = emotion;
        this.bodyText = bodyText;
        this.opened = opened;
        this.anonymous = anonymous;
        this.deleted = deleted;
        this.modified = modified;
        this.feelingsOfEmpathy = feelingsOfEmpathy;
    }
}
