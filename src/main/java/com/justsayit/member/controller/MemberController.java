package com.justsayit.member.controller;

import com.justsayit.core.template.response.BaseResponse;
import com.justsayit.member.controller.request.BlockMemberReq;
import com.justsayit.member.controller.request.JoinReq;
import com.justsayit.member.controller.request.NaverLoginReq;
import com.justsayit.member.controller.request.UpdateProfileReq;
import com.justsayit.member.service.auth.dto.OAuthLoginRes;
import com.justsayit.member.service.auth.naver.NaverLoginCommand;
import com.justsayit.member.service.auth.usecase.OAuthUseCase;
import com.justsayit.member.service.management.JoinFacade;
import com.justsayit.member.service.management.command.BlockMemberCommand;
import com.justsayit.member.service.management.dto.JoinRes;
import com.justsayit.member.service.management.usecase.MemberManagementUseCase;
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

    private final JoinFacade joinFacade;
    private final UpdateProfileFacade updateProfileFacade;
    private final OAuthUseCase oAuthUseCase;
    private final ProfileUseCase profileUseCase;
    private final MemberManagementUseCase memberManagementUseCase;

    @PostMapping("/oauth/naver")
    public ResponseEntity<BaseResponse<OAuthLoginRes>> naverLogin(@RequestBody NaverLoginReq req) {
        OAuthLoginRes res = oAuthUseCase.naverLogin(new NaverLoginCommand(req.getToken()));
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @PostMapping("/management/join")
    public ResponseEntity<BaseResponse<JoinRes>> join(@RequestPart(value = "joinInfo") JoinReq req, @RequestPart(value = "profileImg", required = false) MultipartFile multipartFile) {
        JoinRes res = joinFacade.login(req, multipartFile);
        return ResponseEntity.ok(BaseResponse.ofSuccess(res));
    }

    @PostMapping("/management/quit")
    public ResponseEntity<BaseResponse<Object>> quit() {
        memberManagementUseCase.quit();
        return ResponseEntity.ok(BaseResponse.ofSuccess());
    }

    @PostMapping("/management/block")
    public ResponseEntity<BaseResponse<Object>> blockMember(@RequestBody BlockMemberReq req) {
        memberManagementUseCase.blockMember(new BlockMemberCommand(req.getBlockedId()));
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
}
