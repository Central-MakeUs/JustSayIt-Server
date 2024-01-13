package com.justsayit.member.controller;

import com.justsayit.core.template.BaseResponse;
import com.justsayit.member.controller.request.LoginReq;
import com.justsayit.member.service.LoginFacade;
import com.justsayit.member.service.auth.dto.LoginRes;
import com.justsayit.member.service.auth.usecase.AuthUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final LoginFacade loginFacade;
    private final AuthUseCase authUseCase;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginRes>> login(@RequestPart(value = "loginInfo") LoginReq req, @RequestPart(value = "profileImg", required = false) MultipartFile multipartFile) {
        LoginRes res = loginFacade.login(req, multipartFile);
        return ResponseEntity.ok(BaseResponse.ofSuccess("AUTH-001", "로그인 성공", res));  // TODO 응답 메시지 전역변수 처리
    }

    @PostMapping("/quit/{member-id}")
    public ResponseEntity<BaseResponse> quit(@PathVariable(name = "member-id") Long memberId) {
        authUseCase.quit(memberId);
        return ResponseEntity.ok(BaseResponse.ofSuccess("AUTH-002", "회원탈퇴 성공"));
    }
}
