package com.justsayit.story.controller.request;

import lombok.*;

import java.util.List;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class AddStoryReq {

    private String emotion;
    private String content;
    private boolean opened;
    private boolean anonymous;
    private List<String> emotionOfEmpathy;
}
