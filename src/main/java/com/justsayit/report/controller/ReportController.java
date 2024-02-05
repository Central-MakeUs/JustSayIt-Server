package com.justsayit.report.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.report.controller.request.ReportStoryReq;
import com.justsayit.report.service.story.command.ReportStoryCommand;
import com.justsayit.report.service.story.usecase.ReportStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/report")
@RestController
@RequiredArgsConstructor
public class ReportController {

    private final ReportStoryUseCase reportStoryUseCase;


    @PostMapping("/stories")
    public ResponseEntity<BaseResponse<Object>> reportStory(@RequestBody ReportStoryReq req) {
        reportStoryUseCase.reportStory(ReportStoryCommand.builder()
                .storyId(req.getStoryId())
                .reportCode(req.getReportCode())
                .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }
}
