package com.justsayit.story.service.read.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetStoryRes {

    @JsonProperty(value = "hasNext")
    private boolean hasNext;
    private List<StoryInfo> storyInfo;

    public GetStoryRes(boolean hasNext, List<StoryInfo> storyInfo) {
        this.hasNext = hasNext;
        this.storyInfo = storyInfo;
    }

    @Getter
    public static class StoryInfo {
        private Long storyId;
        private String storyUUID;
        private Long writerId;
        @JsonProperty(value="isMine")
        private boolean mine;
        private ProfileInfo profileInfo;
        private StoryMainContent storyMainContent;
        private StoryMetaInfo storyMetaInfo;
        private EmotionOfEmpathy emotionOfEmpathy;
        private ResultOfEmpathize resultOfEmpathize;
        private LocalDateTime createdAt;
        private LocalDateTime updatedAt;

        @Builder
        public StoryInfo(Long storyId, String storyUUID, Long writerId, boolean mine, ProfileInfo profileInfo, StoryMainContent storyMainContent, StoryMetaInfo storyMetaInfo, EmotionOfEmpathy emotionOfEmpathy, ResultOfEmpathize resultOfEmpathize, LocalDateTime createdAt, LocalDateTime updatedAt) {
            this.storyId = storyId;
            this.storyUUID = storyUUID;
            this.writerId = writerId;
            this.mine = mine;
            this.profileInfo = profileInfo;
            this.storyMainContent = storyMainContent;
            this.storyMetaInfo = storyMetaInfo;
            this.emotionOfEmpathy = emotionOfEmpathy;
            this.resultOfEmpathize = resultOfEmpathize;
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
        public static class StoryMainContent {

            private String writerEmotion;
            private String bodyText;
            private List<Photo> photo;

            @Builder
            public StoryMainContent(String writerEmotion, String bodyText, List<Photo> photo) {
                this.writerEmotion = writerEmotion;
                this.bodyText = bodyText;
                this.photo = photo;
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
            @JsonProperty(value = "isModified")
            private boolean modified;

            @Builder
            public StoryMetaInfo(boolean opened, boolean anonymous, boolean modified) {
                this.opened = opened;
                this.anonymous = anonymous;
                this.modified = modified;
            }
        }

        @Getter
        public static class EmotionOfEmpathy {

            private Long totalCount;
            private Long angryCount;
            @JsonProperty(value = "isAngrySelected")
            private boolean angrySelected;
            private Long happinessCount;
            @JsonProperty(value = "isHappinessSelected")
            private boolean happinessSelected;
            private Long sadnessCount;
            @JsonProperty(value = "isSadnessSelected")
            private boolean sadnessSelected;
            private Long surprisedCount;
            @JsonProperty(value = "isSurprisedSelected")
            private boolean surprisedSelected;

            @Builder
            public EmotionOfEmpathy(Long totalCount, Long angryCount, boolean angrySelected, Long happinessCount, boolean happinessSelected, Long sadnessCount, boolean sadnessSelected, Long surprisedCount, boolean surprisedSelected) {
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

            public EmotionOfEmpathy calcTotalCount() {
                this.totalCount = 0L;
                if (angrySelected) {
                    totalCount += angryCount;
                }
                if (happinessSelected) {
                    totalCount += happinessCount;
                }
                if (sadnessSelected) {
                    totalCount += sadnessCount;
                }
                if (surprisedSelected) {
                    totalCount += surprisedCount;
                }
                return this;
            }
        }

        @Getter
        public static class ResultOfEmpathize {
            private String emotionCode;

            @Builder
            public ResultOfEmpathize(String emotionCode) {
                this.emotionCode = emotionCode;
            }
        }
    }
}
