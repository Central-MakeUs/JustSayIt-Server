package com.justsayit.story.service.empathize;

import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.story.domain.Empathy;
import com.justsayit.story.domain.Story;
import com.justsayit.story.exception.AlreadyEmpathizeException;
import com.justsayit.story.exception.EmpathizeMyStoryException;
import com.justsayit.story.exception.NoEmpathizeException;
import com.justsayit.story.exception.NoStoryException;
import com.justsayit.story.repository.EmpathyRepository;
import com.justsayit.story.repository.StoryRepository;
import com.justsayit.story.service.empathize.command.CancelEmpathizeCommand;
import com.justsayit.story.service.empathize.command.EmpathizeCommand;
import com.justsayit.story.service.empathize.usecase.EmpathizeUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

import static java.util.Objects.isNull;
import static java.util.Objects.nonNull;

@Service
@RequiredArgsConstructor
@Transactional
public class EmpathizeService implements EmpathizeUseCase {

    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;
    private final EmpathyRepository empathyRepository;

    @Override
    public void empathize(EmpathizeCommand cmd) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, cmd.getMemberId());
        Story story = storyRepository.findById(cmd.getStoryId())
                .orElseThrow(NoStoryException::new);
        if (isMyStory(member, story)) {
            throw new EmpathizeMyStoryException();
        }
        Empathy empathy = empathyRepository.searchValidEmpathy(cmd.getMemberId(), cmd.getStoryId());
        if (nonNull(empathy)) {
            throw new AlreadyEmpathizeException();
        }
        empathyRepository.save(Empathy.of(member, story, cmd.getEmotionCode()));
    }

    @Override
    public void cancelEmpathize(CancelEmpathizeCommand cmd) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, cmd.getMemberId());
        Story story = storyRepository.findById(cmd.getStoryId())
                .orElseThrow(NoStoryException::new);
        if (isMyStory(member, story)) {
            throw new EmpathizeMyStoryException();
        }
        Empathy empathy = empathyRepository.searchValidEmpathy(cmd.getMemberId(), cmd.getStoryId());
        if (isNull(empathy)) {
            throw new NoEmpathizeException();
        }
        empathy.cancel();
    }

    private static boolean isMyStory(Member member, Story story) {
        return member.getId().equals(story.getMemberId());
    }
}
