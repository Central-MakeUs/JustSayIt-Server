package com.justsayit.story.service.read;

import com.justsayit.member.domain.Member;
import com.justsayit.member.exception.NoMemberException;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.story.domain.Feeling;
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

import java.util.Comparator;
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
        Member member = memberRepository.findById(memberId)
                .orElseThrow(NoMemberException::new);
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
                    Map<Feeling, Long> empathyCountMap = empathyCountDaos.stream()
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
                                    .writerEmotion(story.getMainContent().getFeeling().getCode())
                                    .build())

                            // 공감받고 싶은 감정 정보
                            .feelingsOfEmpathy(GetStoryRes.StoryInfo.FeelingsOfEmpathy.builder()
                                    .totalCount(empathyCountMap.values()
                                            .stream()
                                            .mapToLong(Long::longValue)
                                            .sum())
                                    .angrySelected(story.getFeelingsOfEmpathy().isAngrySelected())
                                    .sadnessSelected(story.getFeelingsOfEmpathy().isSadnessSelected())
                                    .surprisedSelected(story.getFeelingsOfEmpathy().isSurprisedSelected())
                                    .happinessSelected(story.getFeelingsOfEmpathy().isHappinessSelected())
                                    .angryCount(empathyCountMap.get(Feeling.ANGRY))
                                    .sadnessCount(empathyCountMap.get(Feeling.SADNESS))
                                    .surprisedCount(empathyCountMap.get(Feeling.SURPRISED))
                                    .happinessCount(empathyCountMap.get(Feeling.HAPPINESS))
                                    .build())

                            .createdAt(story.getCreatedAt())
                            .updatedAt(story.getUpdatedAt())

                            .build();
                })
                .collect(Collectors.toList());
        return new GetStoryRes(hasNext, res);
    }
}
