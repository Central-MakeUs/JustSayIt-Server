package com.justsayit.member.service.management.command;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class JoinCommand {

    private String email;
    private String provider;
    private String nickname;
    private String profileImg;
    private String gender;
    private LocalDate birth;

    @Builder
    public JoinCommand(String email, String provider, String nickname, String profileImg, String gender, LocalDate birth) {
        this.email = email;
        this.provider = provider;
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.gender = gender;
        this.birth = birth;
    }
}
