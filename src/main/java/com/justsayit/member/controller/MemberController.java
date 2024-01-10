package com.justsayit.member.controller;

import com.justsayit.member.controller.request.LoginReq;
import com.justsayit.member.service.auth.command.LoginCommand;
import com.justsayit.member.service.auth.dto.LoginRes;
import com.justsayit.member.service.auth.usecase.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final LoginUseCase loginUseCase;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq req) {
        LoginRes res = loginUseCase.login(LoginCommand.builder()
                .loginType(req.getLoginType())
                .nickname(req.getNickname())
                .profileImg(req.getProfileImg())
                .token(req.getToken())
                .build());
        return ResponseEntity.ok(res);
    }
}
