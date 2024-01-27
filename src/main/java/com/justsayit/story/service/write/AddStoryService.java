package com.justsayit.story.service.write;

import com.justsayit.infra.s3.dto.StoryPhoto;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.story.domain.*;
import com.justsayit.story.repository.PhotoRepository;
import com.justsayit.story.repository.StoryRepository;
import com.justsayit.story.service.write.command.AddStoryCommand;
import com.justsayit.story.service.write.usecase.AddStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
public class AddStoryService implements AddStoryUseCase {

    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;
    private final PhotoRepository photoRepository;

    @Override
    public void addStory(AddStoryCommand cmd) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, cmd.getMemberId());
        Story story = Story.createStory(
                member.getId(),
                MainContent.of(cmd.getEmotion(), cmd.getContent()),
                MetaInfo.ofNew(cmd.isOpened(), cmd.isAnonymous()),
                FeelingsOfEmpathy.of(cmd.getFeelingsOfEmpathy()));
        storyRepository.save(story);
        List<StoryPhoto> imgInfoList = cmd.getStoryPhotoList();
        if (!imgInfoList.isEmpty()) {
            saveImg(story, imgInfoList);
        }
    }

    private void saveImg(Story story, List<StoryPhoto> imgInfoList) {
        List<Photo> photoList = new ArrayList<>();
        for (StoryPhoto storyPhoto : imgInfoList) {
            photoList.add(Photo.createPhoto(story, storyPhoto.getUrl()));
        }
        photoRepository.saveAll(photoList);
    }
}
