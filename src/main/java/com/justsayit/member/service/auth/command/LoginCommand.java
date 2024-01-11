package com.justsayit.member.service.auth.command;

import lombok.Builder;
import lombok.Getter;
import org.springframework.web.multipart.MultipartFile;

@Getter
public class LoginCommand {

    private String token;
    private String loginType;
    private String nickname;
    private MultipartFile multipartFile;

    @Builder
    public LoginCommand(String token, String loginType, String nickname, MultipartFile multipartFile) {
        this.token = token;
        this.loginType = loginType;
        this.nickname = nickname;
        this.multipartFile = multipartFile;
    }
}
