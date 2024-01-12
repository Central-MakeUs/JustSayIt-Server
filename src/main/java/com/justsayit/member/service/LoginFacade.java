package com.justsayit.member.service;

import com.justsayit.infra.s3.dto.ProfileImgInfo;
import com.justsayit.infra.s3.usecase.UploadImageUseCase;
import com.justsayit.member.controller.request.LoginReq;
import com.justsayit.member.service.auth.command.LoginCommand;
import com.justsayit.member.service.auth.dto.LoginRes;
import com.justsayit.member.service.auth.usecase.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
@Transactional
public class LoginFacade {

    private final UploadImageUseCase uploadImageUseCase;
    private final LoginUseCase loginUseCase;

    public LoginRes login(LoginReq req, MultipartFile multipartFile) {
        ProfileImgInfo profileImgInfo = uploadImageUseCase.uploadProfileImg(multipartFile);
        return loginUseCase.login(LoginCommand.builder()
                .token(req.getToken())
                .nickname(req.getNickname())
                .loginType(req.getLoginType())
                .profileImg(profileImgInfo.getUrl())
                .build());
    }
}
