package com.justsayit.story.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import com.justsayit.member.domain.Member;
import com.justsayit.story.exception.AlreadyCanceledEmpathyException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "EMPAHTY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Empathy extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "empathy_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private Emotion type;

    @Column(name = "status", nullable = false)
    @Enumerated(EnumType.STRING)
    private EmpathyStatus status;

    private Empathy(Member member, Story story, Emotion type, EmpathyStatus status) {
        this.member = member;
        this.story = story;
        this.type = type;
        this.status = status;
    }

    public static Empathy of(Member member, Story story, String emotionCode) {
        return new Empathy(member, story, Emotion.valueOfCode(emotionCode), EmpathyStatus.VALID);
    }

    public void cancel() {
        if (this.status.equals(EmpathyStatus.INVALID)) {
            throw new AlreadyCanceledEmpathyException();
        }
        this.status = EmpathyStatus.INVALID;
    }
}
