package com.justsayit.member.service.management;

import com.justsayit.core.jwt.JwtTokenProvider;
import com.justsayit.core.jwt.dto.JwtToken;
import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.member.domain.*;
import com.justsayit.member.exception.AlreadyExistsMemberException;
import com.justsayit.member.exception.NoMemberException;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.member.service.management.command.BlockMemberCommand;
import com.justsayit.member.service.management.command.JoinCommand;
import com.justsayit.member.service.management.dto.JoinRes;
import com.justsayit.member.service.management.repository.BlockListRepository;
import com.justsayit.member.service.management.usecase.MemberManagementUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Transactional
public class MemberManagementService implements MemberManagementUseCase {

    private final JwtTokenProvider jwtTokenProvider;
    private final MemberRepository memberRepository;
    private final BlockListRepository blockListRepository;

    @Override
    public JoinRes join(JoinCommand cmd) {
        Member member = memberRepository.findByEmailAndProvider(cmd.getEmail(), Provider.valueOf(cmd.getProvider()));
        if (nonNull(member)) {
            throw new AlreadyExistsMemberException();
        }
        Member newMember = createMember(cmd);
        memberRepository.save(newMember);
        JwtToken jwtToken = jwtTokenProvider.createToken(newMember.getId());
        return JoinRes.builder()
                .memberId(newMember.getId())
                .accessToken(jwtToken.getAccessToken())
                .build();
    }

    private Member createMember(JoinCommand cmd) {
        return Member.ofNew(
                cmd.getEmail(),
                cmd.getProvider(),
                ProfileInfo.builder()
                        .nickname(cmd.getNickname())
                        .profileImg(cmd.getProfileImg())
                        .build(),
                PersonalInfo.builder()
                        .gender(cmd.getGender())
                        .birth(cmd.getBirth())
                        .build()
        );
    }

    @Override
    public void quit() {
        Long memberId = AuthServiceHelper.getMemberId();
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        member.deleteAccount();
    }

    @Override
    public void blockMember(BlockMemberCommand cmd) {
        Long memberId = AuthServiceHelper.getMemberId();
        Member blockerMember = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        Member blockedMember = MemberServiceHelper.findExistingMember(memberRepository, cmd.getBlockedId());
        BlockList blockList = new BlockList(blockerMember, blockedMember);
        blockListRepository.save(blockList);
    }
}
