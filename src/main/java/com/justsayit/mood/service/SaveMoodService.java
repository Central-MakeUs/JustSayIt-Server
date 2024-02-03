package com.justsayit.mood.service;

import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.mood.domain.Mood;
import com.justsayit.mood.repository.MoodRepository;
import com.justsayit.mood.service.command.SaveMoodCommand;
import com.justsayit.mood.service.usecase.SaveMoodUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveMoodService implements SaveMoodUseCase {

    private final MemberRepository memberRepository;
    private final MoodRepository moodRepository;

    @Override
    public void saveMood(SaveMoodCommand cmd) {
        Long memberId = AuthServiceHelper.getMemberId();
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        Mood mood = Mood.of(member, cmd.getMoodCode());
        moodRepository.save(mood);
    }
}
