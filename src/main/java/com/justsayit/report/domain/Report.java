package com.justsayit.report.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import com.justsayit.member.domain.Member;
import com.justsayit.story.domain.Story;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "REPORT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Report extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "report_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Enumerated(EnumType.STRING)
    private ReportType reportType;

    private Report(Member member, Story story, ReportType reportType) {
        this.member = member;
        this.story = story;
        this.reportType = reportType;
    }

    public static Report of(Member member, Story story, String reportType) {
        return new Report(member, story, ReportType.valueOfCode(reportType));
    }
}
