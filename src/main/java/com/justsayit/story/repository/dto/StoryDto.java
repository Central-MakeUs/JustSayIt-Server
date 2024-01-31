package com.justsayit.story.repository.dto;

import com.justsayit.story.domain.EmotionOfEmpathy;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class StoryDto {

    private Long storyId;
    private Long writerId;
    private String uuid;
    private String emotion;
    private String bodyText;
    private boolean opened;
    private boolean anonymous;
    private boolean deleted;
    private boolean modified;
    private EmotionOfEmpathy emotionOfEmpathy;

    @QueryProjection
    public StoryDto(Long storyId, Long writerId, String uuid, String emotion, String bodyText, boolean opened, boolean anonymous, boolean deleted, boolean modified, EmotionOfEmpathy emotionOfEmpathy) {
        this.storyId = storyId;
        this.writerId = writerId;
        this.uuid = uuid;
        this.emotion = emotion;
        this.bodyText = bodyText;
        this.opened = opened;
        this.anonymous = anonymous;
        this.deleted = deleted;
        this.modified = modified;
        this.emotionOfEmpathy = emotionOfEmpathy;
    }
}
