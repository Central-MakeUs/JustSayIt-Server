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
import com.justsayit.member.service.management.usecase.ManagementUseCase;
import com.justsayit.member.service.profile.UpdateProfileFacade;
import com.justsayit.member.service.profile.command.GetProfileCmd;
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
    private final ManagementUseCase managementUseCase;

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

    @PostMapping("/quit/{member-id}")
    public ResponseEntity<BaseResponse<Object>> quit(@PathVariable(name = "member-id") Long memberId) {
        authUseCase.quit(memberId);
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @PatchMapping("/profile/me/{member-id}")
    public ResponseEntity<BaseResponse<Object>> updateProfile(@PathVariable(name = "member-id") Long memberId,
                                                              @RequestPart(value = "updateProfile") UpdateProfileReq req,
                                                              @RequestPart(value = "profileImg", required = false) MultipartFile multipartFile) {
        updateProfileFacade.updateProfile(memberId, req, multipartFile);
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @GetMapping("/profile/me/{member-id}")
    public ResponseEntity<BaseResponse<GetProfileRes>> getProfile(@PathVariable(name = "member-id") Long memberId) {
        GetProfileRes getProfileRes = profileUseCase.getProfile(new GetProfileCmd(memberId));
        return ResponseEntity.ok(BaseResponse.ofSuccess(getProfileRes));
    }

    @PostMapping("/block/{member-id}")
    public ResponseEntity<BaseResponse<Object>> blockMember(@PathVariable(name = "member-id") Long memberId,
                                                            @RequestParam(name = "blockedMemberId") Long blockedMemberId) {
        managementUseCase.blockMember(new BlockMemberCommand(memberId, blockedMemberId));
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }
}
