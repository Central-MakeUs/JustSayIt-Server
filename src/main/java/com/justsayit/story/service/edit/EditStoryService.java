package com.justsayit.story.service.edit;

import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.infra.s3.dto.StoryPhoto;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.story.domain.*;
import com.justsayit.story.exception.NoStoryException;
import com.justsayit.story.exception.NotMyStoryException;
import com.justsayit.story.repository.PhotoRepository;
import com.justsayit.story.repository.StoryRepository;
import com.justsayit.story.service.edit.command.EditStoryCommand;
import com.justsayit.story.service.edit.command.RemoveStoryCommand;
import com.justsayit.story.service.edit.usecase.EditStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional
public class EditStoryService implements EditStoryUseCase {

    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;
    private final PhotoRepository photoRepository;

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

    @Override
    public void edit(EditStoryCommand cmd) {
        Long memberId = AuthServiceHelper.getMemberId();
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        Story story = storyRepository.findById(cmd.getStoryId())
                .orElseThrow(NoStoryException::new);
        if (!story.getMemberId().equals(member.getId())) {
            throw new NotMyStoryException();
        }

        /* 제거된 사진 */

        photoRepository.deleteAllById(cmd.getRemovedPhoto());

        /* 스토리 내용 수정 */
        story.changeMainContent(MainContent.of(cmd.getEmotion(), cmd.getContent()));
        story.changeMetaInfo(MetaInfo.ofModified(cmd.isOpened(), cmd.isAnonymous()));
        story.changeEmotionOfEmpathy(EmotionOfEmpathy.of(cmd.getEmotionOfEmpathy()));

        /* 새롭게 추가된 사진 저장 */
        List<StoryPhoto> imgInfoList = cmd.getNewPhoto();
        if (!imgInfoList.isEmpty()) {
            savePhoto(story, imgInfoList);
        }
    }

    private void savePhoto(Story story, List<StoryPhoto> imgInfoList) {
        List<Photo> photoList = imgInfoList.stream()
                .map(storyPhoto -> Photo.createPhoto(story, storyPhoto.getUrl()))
                .collect(Collectors.toList());
        photoRepository.saveAll(photoList);
    }
}
