package com.justsayit.member.service;

import com.justsayit.member.domain.Member;
import com.justsayit.member.exception.NoMemberException;
import com.justsayit.member.repository.MemberRepository;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PROTECTED)
public final class MemberServiceHelper {

    public static Member findExistingMember(MemberRepository memberRepository, Long memberId) {
        return memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
    }
}
