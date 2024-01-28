package com.justsayit.member.service.management.service;

import com.justsayit.member.domain.BlockList;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.member.service.management.command.BlockMemberCommand;
import com.justsayit.member.service.management.repository.BlockListRepository;
import com.justsayit.member.service.management.usecase.ManagementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ManagementService implements ManagementUseCase {

    private final MemberRepository memberRepository;
    private final BlockListRepository blockListRepository;

    @Override
    public void blockMember(BlockMemberCommand cmd) {
        MemberServiceHelper.findExistingMember(memberRepository, cmd.getBlockerId());
        BlockList blockList = new BlockList(Member.ofRef(cmd.getBlockerId()), Member.ofRef(cmd.getBlockedId()));
        blockListRepository.save(blockList);
    }
}
