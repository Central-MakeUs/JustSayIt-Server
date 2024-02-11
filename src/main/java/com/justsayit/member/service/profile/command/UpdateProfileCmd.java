package com.justsayit.member.service.profile.command;

import lombok.Builder;
import lombok.Getter;

@Getter
public class UpdateProfileCmd {

    private String nickname;
    private String profileImg;

    @Builder
    public UpdateProfileCmd(String nickname, String profileImg) {
        this.nickname = nickname;
        this.profileImg = profileImg;
    }
}
