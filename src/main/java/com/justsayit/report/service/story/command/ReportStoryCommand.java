package com.justsayit.report.service.story.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportStoryCommand {

    private Long memberId;
    private Long storyId;
    private String reportCode;

    @Builder
    public ReportStoryCommand(Long memberId, Long storyId, String reportCode) {
        this.memberId = memberId;
        this.storyId = storyId;
        this.reportCode = reportCode;
    }
}
