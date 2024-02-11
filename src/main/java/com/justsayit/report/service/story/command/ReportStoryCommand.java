package com.justsayit.report.service.story.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class ReportStoryCommand {

    private Long storyId;
    private String reportCode;

    @Builder
    public ReportStoryCommand(Long storyId, String reportCode) {
        this.storyId = storyId;
        this.reportCode = reportCode;
    }
}
