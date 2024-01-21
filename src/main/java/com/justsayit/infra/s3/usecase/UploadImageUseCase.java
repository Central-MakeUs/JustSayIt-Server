package com.justsayit.infra.s3.usecase;

import com.justsayit.infra.s3.dto.ProfileImgInfo;
import com.justsayit.infra.s3.dto.StoryImgInfo;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

public interface UploadImageUseCase {

    ProfileImgInfo uploadProfileImg(MultipartFile multipartFile);

    List<StoryImgInfo> uploadStoryImg(List<MultipartFile> multipartFileList);
}
