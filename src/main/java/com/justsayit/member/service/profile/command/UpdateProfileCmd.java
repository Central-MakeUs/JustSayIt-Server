package com.justsayit.member.service.profile.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateProfileCmd {

    private Long memberId;
    private String nickname;
    private String profileImg;

    @Builder
    public UpdateProfileCmd(Long memberId, String nickname, String profileImg) {
        this.memberId = memberId;
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}
