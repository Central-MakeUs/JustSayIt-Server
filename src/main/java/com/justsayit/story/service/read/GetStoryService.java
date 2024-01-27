package com.justsayit.story.service.read;

import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.story.domain.Emotion;
import com.justsayit.story.domain.Story;
import com.justsayit.story.repository.EmpathyCountRepository;
import com.justsayit.story.repository.StoryRepository;
import com.justsayit.story.repository.dao.EmpathyCountDao;
import com.justsayit.story.service.read.command.StorySearchCondition;
import com.justsayit.story.service.read.dto.GetStoryRes;
import com.justsayit.story.service.read.usecase.GetStoryUseCase;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class GetStoryService implements GetStoryUseCase {

    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;
    private final EmpathyCountRepository empathyCountRepository;

    @Override
    public GetStoryRes getMyPostedStoriesOrderByLatest(Long memberId, StorySearchCondition cond) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        List<Story> storyList = storyRepository.searchMyPostedStoriesOrderByLatest(memberId, cond);

        // 조회 결과가 요청한 size보다 큰 경우
        boolean hasNext = false;
        if (storyList.size() > cond.getSize()) {
            hasNext = true;
            storyList.remove(cond.getSize());
        }

        List<GetStoryRes.StoryInfo> res = storyList.stream()
                .map(story -> {
                    List<EmpathyCountDao> empathyCountDaos = empathyCountRepository.searchMyPostedStoriesEmpathyCount(memberId, story.getId());
                    Map<Emotion, Long> empathyCountMap = empathyCountDaos.stream()
                            .collect(Collectors.toMap(
                                    EmpathyCountDao::getType,
                                    EmpathyCountDao::getCount
                            ));
                    return GetStoryRes.StoryInfo.builder()
                            // id 정보
                            .storyId(story.getId())
                            .storyUUID(story.getUUID())
                            .writerId(story.getMemberId())
                            .mine(memberId.equals(story.getMemberId()))

                            // 작성자 프로필 정보
                            .profileInfo(GetStoryRes.StoryInfo.ProfileInfo.builder()
                                    .nickname(member.getProfileInfo().getNickname())
                                    .profileImg(member.getProfileInfo().getProfileImg())
                                    .build())

                            // 스토리 메타정보
                            .storyMetaInfo(GetStoryRes.StoryInfo.StoryMetaInfo.builder()
                                    .modified(story.getMetaInfo().isModified())
                                    .anonymous(story.getMetaInfo().isAnonymous())
                                    .opened(story.getMetaInfo().isOpened())
                                    .build())

                            // 스토리 메인 컨텐츠
                            .storyMainContent(GetStoryRes.StoryInfo.StoryMainContent.builder()
                                    .bodyText(story.getMainContent().getBodyText())
                                    .photo(story.getPhotoList().stream()
                                            .map(photo -> GetStoryRes.StoryInfo.Photo.builder()
                                                    .photoId(photo.getId())
                                                    .photoUrl(photo.getImgUrl()).build())
                                            .collect(Collectors.toList()))
                                    .writerEmotion(story.getMainContent().getEmotion().getCode())
                                    .build())

                            // 공감받고 싶은 감정 정보
                            .emotionOfEmpathy(GetStoryRes.StoryInfo.EmotionOfEmpathy.builder()
                                    .totalCount(empathyCountMap.values()
                                            .stream()
                                            .mapToLong(Long::longValue)
                                            .sum())
                                    .angrySelected(story.getEmotionOfEmpathy().isAngrySelected())
                                    .sadnessSelected(story.getEmotionOfEmpathy().isSadnessSelected())
                                    .surprisedSelected(story.getEmotionOfEmpathy().isSurprisedSelected())
                                    .happinessSelected(story.getEmotionOfEmpathy().isHappinessSelected())
                                    .angryCount(empathyCountMap.get(Emotion.ANGRY))
                                    .sadnessCount(empathyCountMap.get(Emotion.SADNESS))
                                    .surprisedCount(empathyCountMap.get(Emotion.SURPRISED))
                                    .happinessCount(empathyCountMap.get(Emotion.HAPPINESS))
                                    .build())

                            .createdAt(story.getCreatedAt())
                            .updatedAt(story.getUpdatedAt())

                            .build();
                })
                .collect(Collectors.toList());
        return new GetStoryRes(hasNext, res);
    }

    @Override
    public GetStoryRes getMyPostedStoriesOrderByOldest(Long memberId, StorySearchCondition cond) {
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        List<Story> storyList = storyRepository.searchMyPostedStoriesOrderByOldest(memberId, cond);

        // 조회 결과가 요청한 size보다 큰 경우
        boolean hasNext = false;
        if (storyList.size() > cond.getSize()) {
            hasNext = true;
            storyList.remove(cond.getSize());
        }

        List<GetStoryRes.StoryInfo> res = storyList.stream()
                .map(story -> {
                    List<EmpathyCountDao> empathyCountDaos = empathyCountRepository.searchMyPostedStoriesEmpathyCount(memberId, story.getId());
                    Map<Emotion, Long> empathyCountMap = empathyCountDaos.stream()
                            .collect(Collectors.toMap(
                                    EmpathyCountDao::getType,
                                    EmpathyCountDao::getCount
                            ));
                    return GetStoryRes.StoryInfo.builder()
                            // id 정보
                            .storyId(story.getId())
                            .storyUUID(story.getUUID())
                            .writerId(story.getMemberId())
                            .mine(memberId.equals(story.getMemberId()))

                            // 작성자 프로필 정보
                            .profileInfo(GetStoryRes.StoryInfo.ProfileInfo.builder()
                                    .nickname(member.getProfileInfo().getNickname())
                                    .profileImg(member.getProfileInfo().getProfileImg())
                                    .build())

                            // 스토리 메타정보
                            .storyMetaInfo(GetStoryRes.StoryInfo.StoryMetaInfo.builder()
                                    .modified(story.getMetaInfo().isModified())
                                    .anonymous(story.getMetaInfo().isAnonymous())
                                    .opened(story.getMetaInfo().isOpened())
                                    .build())

                            // 스토리 메인 컨텐츠
                            .storyMainContent(GetStoryRes.StoryInfo.StoryMainContent.builder()
                                    .bodyText(story.getMainContent().getBodyText())
                                    .photo(story.getPhotoList().stream()
                                            .map(photo -> GetStoryRes.StoryInfo.Photo.builder()
                                                    .photoId(photo.getId())
                                                    .photoUrl(photo.getImgUrl()).build())
                                            .collect(Collectors.toList()))
                                    .writerEmotion(story.getMainContent().getEmotion().getCode())
                                    .build())

                            // 공감받고 싶은 감정 정보
                            .emotionOfEmpathy(GetStoryRes.StoryInfo.EmotionOfEmpathy.builder()
                                    .totalCount(empathyCountMap.values()
                                            .stream()
                                            .mapToLong(Long::longValue)
                                            .sum())
                                    .angrySelected(story.getEmotionOfEmpathy().isAngrySelected())
                                    .sadnessSelected(story.getEmotionOfEmpathy().isSadnessSelected())
                                    .surprisedSelected(story.getEmotionOfEmpathy().isSurprisedSelected())
                                    .happinessSelected(story.getEmotionOfEmpathy().isHappinessSelected())
                                    .angryCount(empathyCountMap.get(Emotion.ANGRY))
                                    .sadnessCount(empathyCountMap.get(Emotion.SADNESS))
                                    .surprisedCount(empathyCountMap.get(Emotion.SURPRISED))
                                    .happinessCount(empathyCountMap.get(Emotion.HAPPINESS))
                                    .build())

                            .createdAt(story.getCreatedAt())
                            .updatedAt(story.getUpdatedAt())

                            .build();
                })
                .collect(Collectors.toList());
        return new GetStoryRes(hasNext, res);
    }
}
