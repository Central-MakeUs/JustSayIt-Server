package com.justsayit.member.controller;

import com.justsayit.member.controller.request.LoginReq;
import com.justsayit.member.service.auth.command.LoginCommand;
import com.justsayit.member.service.auth.dto.LoginRes;
import com.justsayit.member.service.auth.usecase.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq req, @RequestPart MultipartFile multipartFile) {
        LoginRes res = loginUseCase.login(LoginCommand.builder()
                .loginType(req.getLoginType())
                .nickname(req.getNickname())
                .multipartFile(multipartFile)
                .token(req.getToken())
                .build());
        return ResponseEntity.ok(res);
    }
}
