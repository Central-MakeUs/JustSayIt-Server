package com.justsayit.member.service.profile.command;

import lombok.Getter;

@Getter
public class GetProfileCmd {

    private Long memberId;

    public GetProfileCmd(Long memberId) {
        this.memberId = memberId;
    }
}
