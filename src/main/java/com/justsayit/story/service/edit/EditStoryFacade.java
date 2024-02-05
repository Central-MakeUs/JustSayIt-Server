package com.justsayit.story.service.edit;

import com.justsayit.infra.s3.dto.StoryPhoto;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import com.justsayit.story.controller.request.EditStoryReq;
import com.justsayit.story.exception.InvalidNumberOfImgException;
import com.justsayit.story.service.edit.command.EditStoryCommand;
import com.justsayit.story.service.edit.usecase.EditStoryUseCase;
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
public class EditStoryFacade {

    private final int MAX_IMG_SIZE = 4;
    private final UploadImageUseCase uploadImageUseCase;
    private final EditStoryUseCase editStoryUseCase;

    public void edit(EditStoryReq req, List<MultipartFile> multipartFileList) {
        List<StoryPhoto> imgInfoList = new ArrayList<>();
        if (!CollectionUtils.isEmpty(multipartFileList)) {
            if (multipartFileList.size() > MAX_IMG_SIZE) {
                throw new InvalidNumberOfImgException();
            }
            imgInfoList = uploadImageUseCase.uploadStoryImg(multipartFileList);
        }
        editStoryUseCase.edit(EditStoryCommand.builder()
                .storyId(req.getStoryId())
                .content(req.getContent())
                .emotion(req.getEmotion())
                .opened(req.isOpened())
                .anonymous(req.isAnonymous())
                .emotionOfEmpathy(req.getEmotionOfEmpathy())
                .newPhoto(imgInfoList)
                .removedPhoto(req.getRemovedPhoto())
                .build());
    }
}