package com.justsayit.report.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.report.service.story.command.ReportStoryCommand;
import com.justsayit.report.service.story.usecase.ReportStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportStoryUseCase reportStoryUseCase;


    @PostMapping("/stories")
    public ResponseEntity<BaseResponse<Object>> reportStory(@RequestParam(name = "story-id") Long storyId,
                                                            @RequestParam(name = "report-code") String reportCode) {
        reportStoryUseCase.reportStory(ReportStoryCommand.builder()
                .storyId(storyId)
                .reportCode(reportCode)
                .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }
}
