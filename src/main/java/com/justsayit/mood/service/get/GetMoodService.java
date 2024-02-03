package com.justsayit.mood.service.get;

import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.mood.domain.Mood;
import com.justsayit.mood.domain.MoodCode;
import com.justsayit.mood.repository.MoodRepository;
import com.justsayit.mood.service.get.dto.GetTodayMoodRes;
import com.justsayit.mood.service.get.usecase.GetMoodUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetMoodService implements GetMoodUseCase {

    private final MemberRepository memberRepository;
    private final MoodRepository moodRepository;

    @Override
    public GetTodayMoodRes getTodayMoodRecord() {
        Long memberId = AuthServiceHelper.getMemberId();
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        List<Mood> moodList = moodRepository.searchTodayMoodOrderByOldest(memberId);
        List<GetTodayMoodRes.MyMood> myMoodList = moodList.stream()
                .map(mood -> GetTodayMoodRes.MyMood.builder()
                        .moodId(mood.getId())
                        .moodCode(MoodCode.codeOfValue(mood.getType()))
                        .createdAt(mood.getCreatedAt())
                        .build())
                .collect(Collectors.toList());
        return GetTodayMoodRes.builder()
                .memberName(member.getProfileInfo().getNickname())
                .myMoodRecord(myMoodList)
                .build();
    }
}
