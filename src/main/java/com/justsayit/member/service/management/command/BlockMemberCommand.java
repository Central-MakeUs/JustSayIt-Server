package com.justsayit.member.service.management.command;

import lombok.Getter;

@Getter
public class BlockMemberCommand {

    private Long blockerId;
    private Long blockedId;

    public BlockMemberCommand(Long blockerId, Long blockedId) {
        this.blockerId = blockerId;
        this.blockedId = blockedId;
    }
}
