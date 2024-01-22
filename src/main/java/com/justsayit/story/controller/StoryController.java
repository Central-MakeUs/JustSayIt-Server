package com.justsayit.story.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.story.controller.request.AddStoryReq;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;
import com.justsayit.story.service.read.usecase.GetStoryUseCase;
import com.justsayit.story.service.write.AddStoryFacade;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
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

//    @GetMapping("/all/{member-id}")
//    public ResponseEntity<BaseResponse<List<GetStoryRes>>> getAllStories(@PathVariable(name = "member-id") Long memberId,
//                                                                         @RequestParam(name = "latest") boolean latest,
//                                                                         @RequestParam(name = "popularity") boolean sortByPopularity,
//                                                                         @RequestParam(name = "emotion") String emotion,
//                                                                         Pageable pageable) {
//        List<GetStoryRes> res = getStoryUseCase.getAllStories(
//                memberId,
//                StorySearchCondition.builder()
//                        .latest(latest)
//                        .sortByPopularity(sortByPopularity)
//                        .emotion(emotion)
//                        .build(),
//                pageable);
//        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
//    }

    @GetMapping("/me/{member-id}")
    public ResponseEntity<BaseResponse<List<GetStoryRes>>> getMyStoriesSortByLatest(@PathVariable(name = "member-id") Long memberId,
                                                                        @RequestParam(name = "sort_by") String sortBy,
                                                                        @RequestParam(name = "emotion") String emotion,
                                                                        Pageable pageable) {
        List<GetStoryRes> res = getStoryUseCase.getMyStories(memberId,
                StorySearchCondition.builder()
                        .sortBy(sortBy)
                        .emotion(emotion)
                        .build(),
                pageable);
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }
}
