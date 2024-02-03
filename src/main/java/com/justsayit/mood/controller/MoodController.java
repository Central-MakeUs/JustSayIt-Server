package com.justsayit.mood.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.mood.controller.request.SaveMoodReq;
import com.justsayit.mood.service.command.SaveMoodCommand;
import com.justsayit.mood.service.usecase.SaveMoodUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/mood")
@RestController
@RequiredArgsConstructor
public class MoodController {

    private final SaveMoodUseCase saveMoodUseCase;

    @PostMapping("/new")
    public ResponseEntity<BaseResponse<Object>> saveMood(@RequestBody SaveMoodReq req) {
        saveMoodUseCase.saveMood(new SaveMoodCommand(req.getMoodCode()));
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }
}
