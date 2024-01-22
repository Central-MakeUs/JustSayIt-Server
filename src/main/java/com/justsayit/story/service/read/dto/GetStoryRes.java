package com.justsayit.story.service.read.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetStoryRes {

    private Long storyId;
    private String storyUUID;
    private Long writerId;
    @JsonProperty(value="isMine")
    private boolean mine;
    private ProfileInfo profileInfo;
    private StoryMainInfo storyInfo;
    private StoryMetaInfo storyMetaInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public GetStoryRes(Long storyId, String storyUUID, Long writerId, boolean mine, ProfileInfo profileInfo, StoryMainInfo storyInfo, StoryMetaInfo storyMetaInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.storyId = storyId;
        this.storyUUID = storyUUID;
        this.writerId = writerId;
        this.mine = mine;
        this.profileInfo = profileInfo;
        this.storyInfo = storyInfo;
        this.storyMetaInfo = storyMetaInfo;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    @Getter
    public static class ProfileInfo {

        private String profileImg;
        private String nickname;

        @Builder
        public ProfileInfo(String profileImg, String nickname) {
            this.profileImg = profileImg;
            this.nickname = nickname;
        }
    }

    @Getter
    public static class StoryMainInfo {

        private String writerEmotion;
        private String bodyText;
        private List<String> photos;

        @Builder
        public StoryMainInfo(String writerEmotion, String bodyText, List<String> photos) {
            this.writerEmotion = writerEmotion;
            this.bodyText = bodyText;
            this.photos = photos;
        }
    }

    @Getter
    public static class StoryMetaInfo {

        @JsonProperty(value="isOpened")
        private boolean opened;
        @JsonProperty(value="isAnonymous")
        private boolean anonymous;
        @JsonProperty(value="isDeleted")
        private boolean deleted;
        @JsonProperty(value = "isModified")
        private boolean modified;

        @Builder
        public StoryMetaInfo(boolean opened, boolean anonymous, boolean deleted, boolean modified) {
            this.opened = opened;
            this.anonymous = anonymous;
            this.deleted = deleted;
            this.modified = modified;
        }
    }
}
