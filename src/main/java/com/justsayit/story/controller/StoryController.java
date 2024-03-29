package com.justsayit.story.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.story.controller.request.AddStoryReq;
import com.justsayit.story.controller.request.EditStoryReq;
import com.justsayit.story.controller.request.EmpathizeReq;
import com.justsayit.story.service.edit.EditStoryFacade;
import com.justsayit.story.service.edit.command.RemoveStoryCommand;
import com.justsayit.story.service.edit.usecase.EditStoryUseCase;
import com.justsayit.story.service.empathize.command.CancelEmpathizeCommand;
import com.justsayit.story.service.empathize.command.EmpathizeCommand;
import com.justsayit.story.service.empathize.usecase.EmpathizeUseCase;
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
    private final EditStoryFacade editStoryFacade;
    private final GetStoryUseCase getStoryUseCase;
    private final EditStoryUseCase editStoryUseCase;
    private final EmpathizeUseCase empathizeUseCase;

    @PostMapping("/new")
    public ResponseEntity<BaseResponse<Object>> addStory(
            @RequestPart(value = "storyInfo") AddStoryReq req,
            @RequestPart(value = "storyImg", required = false) List<MultipartFile> multipartFileList) {
        addStoryFacade.addStory(req, multipartFileList);
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @GetMapping("/me/latest")
    public ResponseEntity<BaseResponse<GetStoryRes>> getMyStoriesOrderByLatest(@RequestParam(name = "story-id", required = false) Long storyId,
                                                                               @RequestParam(name = "emotion-code", required = false) String emotionCode,
                                                                               @RequestParam(name = "size") int size) {
        GetStoryRes res = getStoryUseCase.getMyStoriesOrderByLatest(
                StorySearchCondition.builder()
                        .storyId(storyId)
                        .emotion(emotionCode)
                        .size(size)
                        .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/me/oldest")
    public ResponseEntity<BaseResponse<GetStoryRes>> getMyStoriesOrderByOldest(@RequestParam(name = "story-id", required = false) Long storyId,
                                                                               @RequestParam(name = "emotion-code", required = false) String emotionCode,
                                                                               @RequestParam(name = "size") int size) {
        GetStoryRes res = getStoryUseCase.getMyStoriesOrderByOldest(
                StorySearchCondition.builder()
                        .storyId(storyId)
                        .emotion(emotionCode)
                        .size(size)
                        .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/all/latest")
    public ResponseEntity<BaseResponse<GetStoryRes>> getAllStoriesOrderByLatest(@RequestParam(name = "story-id", required = false) Long storyId,
                                                                                @RequestParam(name = "emotion-code", required = false) String emotionCode,
                                                                                @RequestParam(name = "size") int size) {
        GetStoryRes res = getStoryUseCase.getAllStoriesOrderByLatest(
                StorySearchCondition.builder()
                        .storyId(storyId)
                        .emotion(emotionCode)
                        .size(size)
                        .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @PatchMapping("/remove")
    public ResponseEntity<BaseResponse<Object>> removeMyStory(@RequestParam(name = "story-id") Long storyId) {
        editStoryUseCase.remove(new RemoveStoryCommand(storyId));
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @PostMapping("/empathy")
    public ResponseEntity<BaseResponse<Object>> empathize(@RequestBody EmpathizeReq req) {
        empathizeUseCase.empathize(EmpathizeCommand.builder()
                .storyId(req.getStoryId())
                .emotionCode(req.getEmotionCode())
                .build());
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @PatchMapping("/empathy")
    public ResponseEntity<BaseResponse<Object>> cancelEmpathize(@RequestParam(name = "story-id") Long storyId) {
        empathizeUseCase.cancelEmpathize(new CancelEmpathizeCommand(storyId));
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @PatchMapping("/edit")
    public ResponseEntity<BaseResponse<Object>> edit(@RequestPart(value = "storyInfo") EditStoryReq req,
                                                          @RequestPart(value = "newImg", required = false) List<MultipartFile> multipartFileList) {
        editStoryFacade.edit(req, multipartFileList);
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }
}
