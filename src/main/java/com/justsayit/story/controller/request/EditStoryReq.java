package com.justsayit.story.controller.request;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EditStoryReq {

    private Long storyId;
    private String emotion;
    private String content;
    private boolean opened;
    private boolean anonymous;
    private List<String> emotionOfEmpathy;
    private List<Long> removedPhoto;
}
