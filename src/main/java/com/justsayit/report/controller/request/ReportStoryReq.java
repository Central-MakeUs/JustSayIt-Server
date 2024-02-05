package com.justsayit.report.controller.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ReportStoryReq {

    private Long storyId;
    private String reportCode;
}
