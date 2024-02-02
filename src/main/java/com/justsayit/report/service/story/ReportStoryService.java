package com.justsayit.report.service.story;

import com.justsayit.core.security.auth.AuthServiceHelper;
import com.justsayit.member.domain.Member;
import com.justsayit.member.repository.MemberRepository;
import com.justsayit.member.service.MemberServiceHelper;
import com.justsayit.report.domain.Report;
import com.justsayit.report.exception.ReportMyStoryException;
import com.justsayit.report.repository.ReportRepository;
import com.justsayit.report.service.story.command.ReportStoryCommand;
import com.justsayit.report.service.story.usecase.ReportStoryUseCase;
import com.justsayit.story.domain.Story;
import com.justsayit.story.exception.NoStoryException;
import com.justsayit.story.repository.StoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ReportStoryService implements ReportStoryUseCase {

    private final MemberRepository memberRepository;
    private final StoryRepository storyRepository;
    private final ReportRepository reportRepository;

    @Override
    public void reportStory(ReportStoryCommand cmd) {
        Long memberId = AuthServiceHelper.getMemberId();
        Member member = MemberServiceHelper.findExistingMember(memberRepository, memberId);
        Story story = storyRepository.findById(cmd.getStoryId())
                .orElseThrow(NoStoryException::new);
        if (isMyStory(member, story)) {
            throw new ReportMyStoryException();
        }
        Report report = Report.of(member, story, cmd.getReportCode());
        reportRepository.save(report);
    }

    private boolean isMyStory(Member member, Story story) {
        return story.getMemberId().equals(member.getId());
    }
}
