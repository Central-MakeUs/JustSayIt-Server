package com.justsayit.member.controller;

import com.justsayit.member.controller.request.LoginReq;
import com.justsayit.member.service.LoginFacade;
import com.justsayit.member.service.auth.dto.LoginRes;
import com.justsayit.member.service.auth.usecase.LoginUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final LoginUseCase loginUseCase;
    private final LoginFacade loginFacade;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestPart(value = "login-info") LoginReq req, @RequestPart(value = "file", required = false) MultipartFile multipartFile) {
        LoginRes res = loginFacade.login(req, multipartFile);
        return ResponseEntity.ok(res);
    }
}
