package com.justsayit.member.service.management.command;

import lombok.Getter;

@Getter
public class BlockMemberCommand {

    private Long blockedId;

    public BlockMemberCommand(Long blockedId) {
        this.blockedId = blockedId;
    }
}
