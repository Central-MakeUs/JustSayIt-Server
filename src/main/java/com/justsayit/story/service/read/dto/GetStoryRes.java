package com.justsayit.story.service.read.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetStoryRes {

    private Long storyId;
    private String uuid;
    private Long writerId;
    private boolean mine;
    private ProfileInfo profileInfo;
    private StoryMainInfo storyInfo;
    private StoryMetaInfo storyMetaInfo;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public GetStoryRes(Long storyId, String uuid, Long writerId, boolean mine, ProfileInfo profileInfo, StoryMainInfo storyInfo, StoryMetaInfo storyMetaInfo, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.storyId = storyId;
        this.uuid = uuid;
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

        private boolean opened;
        private boolean anonymous;
        private boolean deleted;
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
