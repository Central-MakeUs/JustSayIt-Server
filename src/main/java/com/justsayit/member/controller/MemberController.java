package com.justsayit.member.controller;

import com.justsayit.member.controller.request.LoginReq;
import com.justsayit.member.service.LoginFacade;
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
    private final LoginFacade loginFacade;

    @PostMapping("/login")
    public ResponseEntity<LoginRes> login(@RequestBody LoginReq req, @RequestPart MultipartFile multipartFile) {
        LoginRes res = loginFacade.login(req, multipartFile);
        return ResponseEntity.ok(res);
    }
}
