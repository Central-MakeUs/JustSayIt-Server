package com.justsayit.report.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.report.service.story.command.ReportStoryCommand;
import com.justsayit.report.service.story.usecase.ReportStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportStoryUseCase reportStoryUseCase;


    @PostMapping("/stories/{story-id}/{member-id}")
    public ResponseEntity<BaseResponse<Object>> reportStory(@PathVariable(name = "member-id") Long memberId,
                                                            @PathVariable(name = "story-id") Long storyId,
                                                            @RequestParam(name = "report-code") String reportCode) {
        reportStoryUseCase.reportStory(ReportStoryCommand.builder()
                .memberId(memberId)
                .storyId(storyId)
                .reportCode(reportCode)
                .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }
}
