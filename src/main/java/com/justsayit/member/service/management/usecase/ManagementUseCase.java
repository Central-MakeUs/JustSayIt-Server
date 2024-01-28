package com.justsayit.member.service.management.usecase;

import com.justsayit.member.service.management.command.BlockMemberCommand;

public interface ManagementUseCase {

    void blockMember(BlockMemberCommand blockMemberCommand);
}
