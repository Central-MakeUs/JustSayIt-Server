package com.justsayit.member.service.profile.dto;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDate;

@Getter
public class GetProfileRes {

    private Long memberId;
    private String provider;
    private ProfileInfo profileInfo;
    private PersonalInfo personalInfo;

    @Builder
    public GetProfileRes(Long memberId, String provider, ProfileInfo profileInfo, PersonalInfo personalInfo) {
        this.memberId = memberId;
        this.provider = provider;
        this.profileInfo = profileInfo;
        this.personalInfo = personalInfo;
    }

    @Getter
    public static class ProfileInfo {

        private String nickname;
        private String profileImg;

        public ProfileInfo(String nickname, String profileImg) {
            this.nickname = nickname;
            this.profileImg = profileImg;
        }
    }

    @Getter
    public static class PersonalInfo {

        private String gender;
        private LocalDate birth;

        public PersonalInfo(String gender, LocalDate birth) {
            this.gender = gender;
            this.birth = birth;
        }
    }
}
