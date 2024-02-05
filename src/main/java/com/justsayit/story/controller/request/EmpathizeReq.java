package com.justsayit.story.controller.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmpathizeReq {

    private Long storyId;
    private String emotionCode;
}
