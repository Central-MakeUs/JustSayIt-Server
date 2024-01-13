package com.justsayit.member.service.profile;

import com.justsayit.member.domain.Member;
import com.justsayit.member.domain.ProfileInfo;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.profile.command.UpdateProfileCmd;
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
        Member member = memberRepository.findById(cmd.getMemberId())
                .orElseThrow();
        member.updateProfile(ProfileInfo.builder()
                .nickname(cmd.getNickname())
                .profileImg(cmd.getProfileImg())
                .build());
    }
}
