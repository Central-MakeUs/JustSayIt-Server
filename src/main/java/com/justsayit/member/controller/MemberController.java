package com.justsayit.member.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.member.controller.request.LoginReq;
import com.justsayit.member.controller.request.UpdateProfileReq;
import com.justsayit.member.service.auth.LoginFacade;
import com.justsayit.member.service.auth.command.CheckIsJoinedCmd;
import com.justsayit.member.service.auth.dto.CheckIsJoinedRes;
import com.justsayit.member.service.auth.dto.LoginRes;
import com.justsayit.member.service.auth.usecase.AuthUseCase;
import com.justsayit.member.service.management.command.BlockMemberCommand;
import com.justsayit.member.service.management.usecase.ManageMemberUseCase;
import com.justsayit.member.service.profile.UpdateProfileFacade;
import com.justsayit.member.service.profile.dto.GetProfileRes;
import com.justsayit.member.service.profile.usecase.ProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RequestMapping("/members")
@RestController
@RequiredArgsConstructor
public class MemberController {

    private final LoginFacade loginFacade;
    private final UpdateProfileFacade updateProfileFacade;
    private final AuthUseCase authUseCase;
    private final ProfileUseCase profileUseCase;
    private final ManageMemberUseCase manageMemberUseCase;

    @PostMapping("/login")
    public ResponseEntity<BaseResponse<LoginRes>> login(@RequestPart(value = "loginInfo") LoginReq req, @RequestPart(value = "profileImg", required = false) MultipartFile multipartFile) {
        LoginRes res = loginFacade.login(req, multipartFile);
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @GetMapping("/check")
    public ResponseEntity<BaseResponse<CheckIsJoinedRes>> checkIsJoined(@RequestParam("token") String token) {
        CheckIsJoinedRes res = authUseCase.checkIsJoined(new CheckIsJoinedCmd(token));
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @PostMapping("/quit")
    public ResponseEntity<BaseResponse<Object>> quit() {
        authUseCase.quit();
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @PatchMapping("/profile/me")
    public ResponseEntity<BaseResponse<Object>> updateProfile(@RequestPart(value = "updateProfile") UpdateProfileReq req,
                                                              @RequestPart(value = "profileImg", required = false) MultipartFile multipartFile) {
        updateProfileFacade.updateProfile(req, multipartFile);
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @GetMapping("/profile/me")
    public ResponseEntity<BaseResponse<GetProfileRes>> getProfile() {
        GetProfileRes getProfileRes = profileUseCase.getProfile();
        return ResponseEntity.ok(BaseResponse.ofSuccess(getProfileRes));
    }

    @PostMapping("/block")
    public ResponseEntity<BaseResponse<Object>> blockMember(@RequestParam(name = "blocked-id") Long blockedId) {
        manageMemberUseCase.blockMember(new BlockMemberCommand(blockedId));
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }
}
