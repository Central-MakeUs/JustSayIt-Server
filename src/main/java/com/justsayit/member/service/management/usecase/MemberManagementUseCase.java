package com.justsayit.member.service.management.usecase;

import com.justsayit.member.service.management.command.BlockMemberCommand;
import com.justsayit.member.service.management.command.JoinCommand;
import com.justsayit.member.service.management.dto.JoinRes;

public interface MemberManagementUseCase {

    JoinRes join(JoinCommand cmd);

    void quit();

    void blockMember(BlockMemberCommand blockMemberCommand);
}
