package com.justsayit.mood.service.get.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.List;

@Getter
public class GetTodayMoodRes {

    private String memberName;
    private List<MyMood> myMoodRecord;

    @Getter
    public static class MyMood {

        private Long moodId;
        private String moodCode;
        private LocalDateTime createdAt;

        @Builder
        public MyMood(Long moodId, String moodCode, LocalDateTime createdAt) {
            this.moodId = moodId;
            this.moodCode = moodCode;
            this.createdAt = createdAt;
        }
    }

    @Builder
    public GetTodayMoodRes(String memberName, List<MyMood> myMoodRecord) {
        this.memberName = memberName;
        this.myMoodRecord = myMoodRecord;
    }
}
