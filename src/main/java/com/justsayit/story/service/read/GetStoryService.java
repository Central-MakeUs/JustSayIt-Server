package com.justsayit.story.service.read;

import com.justsayit.member.domain.Member;
import com.justsayit.member.exception.NoMemberException;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.story.domain.Story;
import com.justsayit.story.repository.StoryRepository;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;
import com.justsayit.story.service.read.usecase.GetStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

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
        List<Story> storyList = storyRepository.searchMyStoriesOrderByLatest(memberId, cond, pageable);
        return storyList.stream()
                .map(story -> GetStoryRes.builder()
                        .createdAt(story.getCreatedAt())
                        .updatedAt(story.getUpdatedAt())
                        .storyId(story.getId())
                        .mine(story.getMemberId().equals(memberId))
                        .storyUUID(story.getUuid())
                        .writerId(story.getMemberId())
                        .storyMainInfo(GetStoryRes.StoryMainInfo.builder()
                                .bodyText(story.getMainContent().getBodyText())
                                .photo(story.getPhotoList()
                                        .stream()
                                        .map(photo -> GetStoryRes.Photo.builder()
                                                .photoId(photo.getId())
                                                .photoUrl(photo.getImgUrl())
                                                .build())
                                        .collect(Collectors.toList()))
                                .writerEmotion(story.getMainContent().getFeeling().toString())
                                .build())
                        .storyMetaInfo(GetStoryRes.StoryMetaInfo.builder()
                                .opened(story.getMetaInfo().isOpened())
                                .anonymous(story.getMetaInfo().isAnonymous())
                                .modified(story.getMetaInfo().isModified())
                                .build())
                        .profileInfo(GetStoryRes.ProfileInfo.builder()
                                .nickname(member.getProfileInfo().getNickname())
                                .profileImg(member.getProfileInfo().getProfileImg())
                                .build())
                        .build())
                .collect(Collectors.toList());
    }
}
