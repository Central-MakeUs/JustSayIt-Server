package com.justsayit.story.service.edit;

import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.story.domain.Story;
import com.justsayit.story.exception.NoStoryException;
import com.justsayit.story.exception.NotMyStoryException;
import com.justsayit.story.repository.StoryRepository;
import com.justsayit.story.service.edit.command.RemoveStoryCommand;
import com.justsayit.story.service.edit.usecase.EditStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class EditStoryService implements EditStoryUseCase {

    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;

    @Override
    public void remove(RemoveStoryCommand cmd) {
        Long memberId = AuthServiceHelper.getMemberId();
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        Story story = storyRepository.findById(cmd.getStoryId())
                .orElseThrow(NoStoryException::new);
        if (!story.getMemberId().equals(member.getId())) {
            throw new NotMyStoryException();
        }
        story.remove();
    }
}
