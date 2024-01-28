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
        Member blockerMember = MemberServiceHelper.findExistingMember(memberRepository, cmd.getBlockerId());
        Member blockedMember = MemberServiceHelper.findExistingMember(memberRepository, cmd.getBlockedId());
        BlockList blockList = new BlockList(Member.ofRef(blockerMember.getId()), Member.ofRef(blockedMember.getId()));
        blockListRepository.save(blockList);
    }
}
