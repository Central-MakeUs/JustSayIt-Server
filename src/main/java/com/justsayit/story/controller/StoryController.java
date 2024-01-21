package com.justsayit.story.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.story.controller.request.AddStoryReq;
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

    @PostMapping("/new/{member-id}")
    public ResponseEntity<BaseResponse<Object>> addStory(
            @PathVariable(name = "member-id") Long memberId,
            @RequestPart(value = "storyInfo") AddStoryReq req,
            @RequestPart(value = "storyImg", required = false) List<MultipartFile> multipartFileList) {
        addStoryFacade.addStory(memberId, req, multipartFileList);
    }
}
