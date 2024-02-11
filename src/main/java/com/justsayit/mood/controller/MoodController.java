package com.justsayit.mood.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.mood.controller.request.SaveMoodReq;
import com.justsayit.mood.service.get.dto.GetTodayMoodRes;
import com.justsayit.mood.service.get.usecase.GetMoodUseCase;
import com.justsayit.mood.service.save.command.SaveMoodCommand;
import com.justsayit.mood.service.save.usecase.SaveMoodUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RequestMapping("/mood")
@RestController
@RequiredArgsConstructor
public class MoodController {

    private final SaveMoodUseCase saveMoodUseCase;
    private final GetMoodUseCase getMoodUseCase;

    @PostMapping("/new")
    public ResponseEntity<BaseResponse<Object>> saveMood(@RequestBody SaveMoodReq req) {
        saveMoodUseCase.saveMood(new SaveMoodCommand(req.getMoodCode()));
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @GetMapping("/today")
    public ResponseEntity<BaseResponse<GetTodayMoodRes>> getTodayMoodRecord() {
        GetTodayMoodRes res = getMoodUseCase.getTodayMoodRecord();
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}
