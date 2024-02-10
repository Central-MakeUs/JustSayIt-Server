package com.justsayit.member.service.management;

import com.justsayit.infra.s3.dto.ProfileImgInfo;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import com.justsayit.member.controller.request.JoinReq;
import com.justsayit.member.service.management.command.JoinCommand;
import com.justsayit.member.service.management.dto.JoinRes;
import com.justsayit.member.service.management.usecase.MemberManagementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Objects;

@Service
@RequiredArgsConstructor
@Transactional
public class JoinFacade {

    private final UploadImageUseCase uploadImageUseCase;
    private final MemberManagementUseCase memberManagementUseCase;

    public JoinRes login(JoinReq req, MultipartFile multipartFile) {
        ProfileImgInfo profileImgInfo = ProfileImgInfo.ofDefault();
        if (Objects.nonNull(multipartFile)) {
            profileImgInfo = uploadImageUseCase.uploadProfileImg(multipartFile);
        }
        return memberManagementUseCase.join(JoinCommand.builder()
                .email(req.getEmail())
                .nickname(req.getNickname())
                .provider(req.getProvider())
                .profileImg(profileImgInfo.getUrl())
                .birth(req.getBirth())
                .gender(req.getGender())
                .build());
    }
}
