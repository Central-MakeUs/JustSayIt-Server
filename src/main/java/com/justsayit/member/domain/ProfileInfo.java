package com.justsayit.member.domain;

import com.justsayit.member.exception.MemberNicknameOverflowException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileInfo {

    private String nickname;
    private String profileImg;

    @Builder
    public ProfileInfo(String nickname, String profileImg) {
        validateLength(nickname);
        this.nickname = nickname;
        this.profileImg = profileImg;
    }

    private void validateLength(String nickname) {
        if (nickname.length() < 2 || nickname.length() > 12) {
            throw new MemberNicknameOverflowException();
        }
    }
}
