package com.justsayit.member.service.profile;

import com.justsayit.infra.s3.dto.ProfileImgInfo;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import com.justsayit.member.controller.request.UpdateProfileReq;
import com.justsayit.member.service.profile.command.UpdateProfileCmd;
import com.justsayit.member.service.profile.usecase.ProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class UpdateProfileFacade {

    private final UploadImageUseCase uploadImageUseCase;
    private final ProfileUseCase profileUseCase;

    public void updateProfile(Long memberId, UpdateProfileReq req, MultipartFile multipartFile) {
        ProfileImgInfo profileImgInfo = new ProfileImgInfo(req.getProfileImg());
        if (!multipartFile.isEmpty()) {
            profileImgInfo = uploadImageUseCase.uploadProfileImg(multipartFile);
        }
        profileUseCase.updateProfile(UpdateProfileCmd.builder()
                .memberId(memberId)
                .nickname(req.getNickname())
                .profileImg(profileImgInfo.getUrl())
                .build());
    }
}
