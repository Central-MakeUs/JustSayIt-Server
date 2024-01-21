package com.justsayit.story.service.read;

import com.justsayit.member.domain.Member;
import com.justsayit.member.exception.NoMemberException;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.story.repository.StoryRepository;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;
import com.justsayit.story.service.read.usecase.GetStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetStoryService implements GetStoryUseCase {

    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;

    @Override
    public List<GetStoryRes> getMyStories(Long memberId, StorySearchCondition cond, Pageable pageable) {
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
        storyRepository.searchMyStories(memberId, cond, pageable);
        return null;
    }
}
