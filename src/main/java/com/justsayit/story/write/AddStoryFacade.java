package com.justsayit.story.write;

import com.justsayit.infra.s3.dto.StoryImgInfo;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import com.justsayit.story.controller.request.AddStoryReq;
import com.justsayit.story.write.command.AddStoryCommand;
import com.justsayit.story.write.usecase.AddStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddStoryFacade {

    private final UploadImageUseCase uploadImageUseCase;
    private final AddStoryUseCase addStoryUseCase;

    public void addStory(Long memberId, AddStoryReq req, List<MultipartFile> multipartFileList) {
        List<StoryImgInfo> imgInfoList = uploadImageUseCase.uploadStoryImg(multipartFileList);
        addStoryUseCase.addStory(AddStoryCommand.builder()
                .memberId(memberId)
                .emotion(req.getEmotion())
                .content(req.getContent())
                .storyImgInfoList(imgInfoList)
                .opened(req.isOpened())
                .anonymous(req.isAnonymous())
                .feelingsOfEmpathy(req.getFeelingsOfEmpathy())
                .build());
    }
}
