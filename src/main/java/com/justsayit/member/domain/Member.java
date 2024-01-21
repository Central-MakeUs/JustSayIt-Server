package com.justsayit.member.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import lombok.AccessLevel;
import lombok.Builder;
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
    @Column(name = "id")
    private Long id;

    @Column(name = "token", nullable = false)
    private String token;

    @Enumerated(EnumType.STRING)
    @Column(name = "login_type", nullable = false)
    private LoginType loginType;

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
    @CollectionTable(name = "AUTHORITY", joinColumns = @JoinColumn(name = "id"))
    private List<String> authorities = new ArrayList<>();

    @Builder
    public Member(String token, String loginType, ProfileInfo profileInfo, PersonalInfo personalInfo) {
        this.token = token;
        this.loginType = LoginType.valueOf(loginType);
        this.profileInfo = profileInfo;
        this.personalInfo = personalInfo;
        this.status = MemberStatus.ACTIVE;
        this.authorities = List.of("MEMBER");
    }

    public void updateProfile(ProfileInfo profileInfo) {
        this.profileInfo = profileInfo;
    }

    public void deleteAccount() {
        this.status = MemberStatus.INACTIVE;
    }
}
