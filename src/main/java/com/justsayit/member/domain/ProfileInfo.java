package com.justsayit.member.domain;

import com.justsayit.member.exception.NicknameOverflowException;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ProfileInfo {

    private String nickname;
    private String profileImg;
    private Gender gender;
    private LocalDate birth;

    @Builder
    public ProfileInfo(String nickname, String profileImg, String gender, LocalDate birth) {
        validateLength(nickname);
        this.nickname = nickname;
        this.profileImg = profileImg;
        this.gender = Gender.valueOf(gender);
        this.birth = birth;
    }

    private void validateLength(String nickname) {
        if (nickname.length() < 2 || nickname.length() > 12) {
            throw new NicknameOverflowException();
        }
    }
}
