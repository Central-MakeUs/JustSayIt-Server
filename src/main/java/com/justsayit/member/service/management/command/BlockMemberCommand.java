package com.justsayit.member.service.management.command;

import lombok.Getter;

@Getter
public class BlockMemberCommand {

    private Long memberId;
    private Long blockedMemberId;

    public BlockMemberCommand(Long memberId, Long blockedMemberId) {
        this.memberId = memberId;
        this.blockedMemberId = blockedMemberId;
    }
}
