package com.justsayit.member.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "MEMBER")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Member extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;

    @Column(name = "email", nullable = false)
    private String email;

    @Enumerated(EnumType.STRING)
    @Column(name = "provider", nullable = false)
    private Provider provider;

    @Embedded
    @AttributeOverride(name = "nickname", column = @Column(name = "nickname", nullable = false))
    @AttributeOverride(name = "profileImg", column = @Column(name = "profile_img", nullable = false))
    private ProfileInfo profileInfo;


    @Embedded
    @AttributeOverride(name = "gender", column = @Column(name = "gender", nullable = false))
    @AttributeOverride(name = "birth", column = @Column(name = "birth", nullable = false))
    private PersonalInfo personalInfo;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private MemberStatus status;

    @ElementCollection
    @CollectionTable(name = "AUTHORITY", joinColumns = @JoinColumn(name = "member_id"))
    private List<String> authorities = new ArrayList<>();

    public static Member ofNew(String email, String provider, ProfileInfo profileInfo, PersonalInfo personalInfo) {
        return new Member(null, email, Provider.valueOf(provider), profileInfo, personalInfo, MemberStatus.ACTIVE, List.of("MEMBER"));
    }

    public static Member ofRef(Long memberId) {
        return new Member(memberId, null, null, null, null, null, null);
    }

    private  Member(Long id, String email, Provider provider, ProfileInfo profileInfo, PersonalInfo personalInfo, MemberStatus status, List<String> authorities) {
        this.id = id;
        this.email = email;
        this.provider = provider;
        this.profileInfo = profileInfo;
        this.personalInfo = personalInfo;
        this.status = status;
        this.authorities = authorities;
    }

    public void updateProfile(ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }

    public void deleteAccount() {
        this.status = MemberStatus.INACTIVE;
    }
}
