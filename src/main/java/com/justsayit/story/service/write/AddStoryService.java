package com.justsayit.story.service.write;

import com.justsayit.member.repository.MemberRepository;
import com.justsayit.story.service.write.command.AddStoryCommand;
import com.justsayit.story.service.write.usecase.AddStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class AddStoryService implements AddStoryUseCase {

    private final MemberRepository memberRepository;

    @Override
    public void addStory(AddStoryCommand cmd) {

    }
}
