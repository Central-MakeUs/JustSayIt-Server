package com.justsayit.member.service.profile;

import com.justsayit.member.domain.Member;
import com.justsayit.member.domain.ProfileInfo;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.member.service.profile.command.GetProfileCmd;
import com.justsayit.member.service.profile.command.UpdateProfileCmd;
import com.justsayit.member.service.profile.dto.GetProfileRes;
import com.justsayit.member.service.profile.usecase.ProfileUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ProfileService implements ProfileUseCase {

    private final MemberRepository memberRepository;

    @Override
    public void updateProfile(UpdateProfileCmd cmd) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, cmd.getMemberId());
        member.updateProfile(ProfileInfo.builder()
                .nickname(cmd.getNickname())
                .profileImg(cmd.getProfileImg())
                .build());
    }

    @Override
    public GetProfileRes getProfile(GetProfileCmd cmd) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, cmd.getMemberId());
        return GetProfileRes.builder()
                .memberId(member.getId())
                .loginType(member.getLoginType().toString())
                .profileInfo(
                        new GetProfileRes.ProfileInfo(
                                member.getProfileInfo().getNickname(),
                                member.getProfileInfo().getProfileImg()))
                .personalInfo(
                        new GetProfileRes.PersonalInfo(
                                member.getPersonalInfo().getGender().toString(),
                                member.getPersonalInfo().getBirth()
                        ))
                .build();
    }
}
