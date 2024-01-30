package com.justsayit.report.service.story.usecase;

import com.justsayit.report.service.story.command.ReportStoryCommand;

public interface ReportStoryUseCase {

    void reportStory(ReportStoryCommand reportStoryCommand);
}
