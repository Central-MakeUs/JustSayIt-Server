package com.justsayit.mood.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import com.justsayit.member.domain.Member;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "MOOD")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Mood extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "mood_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @Enumerated(EnumType.STRING)
    @Column(name = "mood_type")
    private MoodCode type;

    private Mood(Member member, MoodCode type) {
        this.member = member;
        this.type = type;
    }

    public static Mood of(Member member, String code) {
        return new Mood(member, MoodCode.valueOfCode(code));
    }
}
