package com.justsayit.story.domain;

import com.justsayit.member.domain.Member;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "EMPAHTY_COUNT")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class EmpathyCount {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empathy_count_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Enumerated(EnumType.STRING)
    private Emotion type;

    @Builder
    public EmpathyCount(Member member, Story story, Emotion type) {
        this.member = member;
        this.story = story;
        this.type = type;
    }
}
