package com.justsayit.mood.service.save;

import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.mood.domain.Mood;
import com.justsayit.mood.exception.OneMinuteWithinException;
import com.justsayit.mood.repository.MoodRepository;
import com.justsayit.mood.service.save.command.SaveMoodCommand;
import com.justsayit.mood.service.save.usecase.SaveMoodUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;

import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Transactional
public class SaveMoodService implements SaveMoodUseCase {

    private final int ONE_MINUTE = 1;
    private final MemberRepository memberRepository;
    private final MoodRepository moodRepository;

    @Override
    public void saveMood(SaveMoodCommand cmd) {
        Long memberId = AuthServiceHelper.getMemberId();
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        Mood lastSavedMood = moodRepository.searchLatestMood(memberId);
        if (nonNull(lastSavedMood) && isWithinOneMinute(lastSavedMood)) {
            throw new OneMinuteWithinException();
        }
        Mood mood = Mood.of(member, cmd.getMoodCode());
        moodRepository.save(mood);
    }

    private boolean isWithinOneMinute(Mood lastSavedMood) {
        LocalDateTime now = LocalDateTime.now();
        long minutesDifference = ChronoUnit.MINUTES.between(lastSavedMood.getCreatedAt(), now);
        return Math.abs(minutesDifference) < ONE_MINUTE;
    }
}
