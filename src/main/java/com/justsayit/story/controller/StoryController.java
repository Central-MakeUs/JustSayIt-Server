package com.justsayit.story.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.story.controller.request.AddStoryReq;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;
import com.justsayit.story.service.read.usecase.GetStoryUseCase;
import com.justsayit.story.service.write.AddStoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RequestMapping("/stories")
@RestController
@RequiredArgsConstructor
public class StoryController {

    private final AddStoryFacade addStoryFacade;
    private final GetStoryUseCase getStoryUseCase;

    @PostMapping("/new/{member-id}")
    public ResponseEntity<BaseResponse<Object>> addStory(
            @PathVariable(name = "member-id") Long memberId,
            @RequestPart(value = "storyInfo") AddStoryReq req,
            @RequestPart(value = "storyImg", required = false) List<MultipartFile> multipartFileList) {
        addStoryFacade.addStory(memberId, req, multipartFileList);
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @GetMapping("/me/latest/{member-id}")
    public ResponseEntity<BaseResponse<GetStoryRes>> getMyStoriesSortByLatest(@PathVariable(name = "member-id") Long memberId,
                                                                                    @RequestParam(name = "story-id", required = false) Long storyId,
                                                                                    @RequestParam(name = "emotion-code", required = false) String emotionCode,
                                                                                    @RequestParam(name = "size") int size) {
        GetStoryRes res = getStoryUseCase.getMyPostedStoriesOrderByLatest(
                memberId,
                StorySearchCondition.builder()
                        .storyId(storyId)
                        .emotion(emotionCode)
                        .size(size)
                        .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}
