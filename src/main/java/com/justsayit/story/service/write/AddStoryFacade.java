package com.justsayit.story.service.write;

import com.justsayit.infra.s3.dto.StoryPhoto;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import com.justsayit.story.controller.request.AddStoryReq;
import com.justsayit.story.exception.InvalidNumberOfImgException;
import com.justsayit.story.service.write.command.AddStoryCommand;
import com.justsayit.story.service.write.usecase.AddStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.web.multipart.MultipartFile;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddStoryFacade {

    private final int MAX_IMG_SIZE = 4;
    private final UploadImageUseCase uploadImageUseCase;
    private final AddStoryUseCase addStoryUseCase;

    public void addStory(AddStoryReq req, List<MultipartFile> multipartFileList) {
        List<StoryPhoto> imgInfoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(multipartFileList)) {
            if (multipartFileList.size() > MAX_IMG_SIZE) {
                throw new InvalidNumberOfImgException();
            }
            imgInfoList = uploadImageUseCase.uploadStoryImg(multipartFileList);
        }
        addStoryUseCase.addStory(AddStoryCommand.builder()
                .emotion(req.getEmotion())
                .content(req.getContent())
                .storyPhotoList(imgInfoList)
                .opened(req.isOpened())
                .anonymous(req.isAnonymous())
                .emotionOfEmpathy(req.getEmotionOfEmpathy())
                .build());
    }
}
