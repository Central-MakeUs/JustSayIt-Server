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
    private StoryMainInfo storyMainInfo;
    private StoryMetaInfo storyMetaInfo;
    private FeelingsOfEmpathy feelingsOfEmpathy;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    @Builder
    public GetStoryRes(Long storyId, String storyUUID, Long writerId, boolean mine, ProfileInfo profileInfo, StoryMainInfo storyMainInfo, StoryMetaInfo storyMetaInfo, FeelingsOfEmpathy feelingsOfEmpathy, LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.storyId = storyId;
        this.storyUUID = storyUUID;
        this.writerId = writerId;
        this.mine = mine;
        this.profileInfo = profileInfo;
        this.storyMainInfo = storyMainInfo;
        this.storyMetaInfo = storyMetaInfo;
        this.feelingsOfEmpathy = feelingsOfEmpathy;
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
        private List<Photo> photos;

        @Builder
        public StoryMainInfo(String writerEmotion, String bodyText, List<Photo> photos) {
            this.writerEmotion = writerEmotion;
            this.bodyText = bodyText;
            this.photos = photos;
        }
    }

    @Getter
    public static class Photo {

        private Long photoId;
        private String photoUrl;

        @Builder
        public Photo(Long photoId, String photoUrl) {
            this.photoId = photoId;
            this.photoUrl = photoUrl;
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

    @Getter
    public static class FeelingsOfEmpathy {

        private Long feelingId;
        private int totalCount;
        private int angryCount;
        @JsonProperty(value = "isAngrySelected")
        private boolean angrySelected;
        private int happinessCount;
        @JsonProperty(value = "isHappinessSelected")
        private boolean happinessSelected;
        private int sadnessCount;
        @JsonProperty(value = "isSadnessSelected")
        private boolean sadnessSelected;
        private int surprisedCount;
        @JsonProperty(value = "isSurprisedSelected")
        private boolean surprisedSelected;

        @Builder
        public FeelingsOfEmpathy(Long feelingId, int totalCount, int angryCount, boolean angrySelected, int happinessCount, boolean happinessSelected, int sadnessCount, boolean sadnessSelected, int surprisedCount, boolean surprisedSelected) {
            this.feelingId = feelingId;
            this.totalCount = totalCount;
            this.angryCount = angryCount;
            this.angrySelected = angrySelected;
            this.happinessCount = happinessCount;
            this.happinessSelected = happinessSelected;
            this.sadnessCount = sadnessCount;
            this.sadnessSelected = sadnessSelected;
            this.surprisedCount = surprisedCount;
            this.surprisedSelected = surprisedSelected;
        }
    }
}
