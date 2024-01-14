package com.justsayit.member.domain;

import com.justsayit.member.exception.InvalidNicknameLengthException;
import org.assertj.core.internal.bytebuddy.utility.RandomString;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ProfileInfoTest {

    @DisplayName("닉네임이 2글자 이상, 12글자 이하라면 사진과 함께 프로필을 수정한다")
    @Test
    void updateSuccess() {
        // given
        Member member = Member.builder()
                .token("토큰1")
                .loginType("NAVER")
                .profileInfo(ProfileInfo.builder()
                        .nickname("닉네임1")
                        .profileImg("프로필1")
                        .build())
                .build();

        // when
        String randomNickname = RandomString.make((int) (Math.random() * 12) + 2);
        member.updateProfile(ProfileInfo.builder()
                .nickname(randomNickname)
                .profileImg("프로필1")
                .build());

        // then
        assertThat(member.getProfileInfo()).extracting(ProfileInfo::getNickname, ProfileInfo::getProfileImg)
                .containsExactly(randomNickname, "프로필1");
    }

    @DisplayName("닉네임이 한 글자 또는 12글자 초과라면 MemberNicknameOverflow 예외를 던진다")
    @Test
    void thrNicknameOverflowException() {
        // given
        Member member = Member.builder()
                .token("토큰1")
                .loginType("NAVER")
                .profileInfo(ProfileInfo.builder()
                        .nickname("닉네임1")
                        .profileImg("프로필1")
                        .build())
                .build();

        // when
        String randomNickname = RandomString.make((int) (Math.random() * 100) + 13);

        // then
        assertThatThrownBy(() -> member.updateProfile(ProfileInfo.builder()
                .nickname(randomNickname)
                .profileImg("프로필1")
                .build()))
                .isInstanceOf(InvalidNicknameLengthException.class);
    }
}