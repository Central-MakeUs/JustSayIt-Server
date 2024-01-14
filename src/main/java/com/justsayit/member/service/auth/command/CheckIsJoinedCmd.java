package com.justsayit.member.service.auth.command;

import lombok.Getter;

@Getter
public class CheckIsJoinedCmd {

    private String token;

    public CheckIsJoinedCmd(String token) {
        this.token = token;
    }
}
