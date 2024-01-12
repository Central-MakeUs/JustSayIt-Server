package com.justsayit.infra.s3.usecase;

import com.justsayit.infra.s3.dto.ProfileImgInfo;
import org.springframework.web.multipart.MultipartFile;

public interface UploadImageUseCase {

    ProfileImgInfo uploadProfileImg(MultipartFile multipartFile);
}
