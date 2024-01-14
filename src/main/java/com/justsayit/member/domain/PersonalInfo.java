package com.justsayit.member.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalInfo {

    private Gender gender;
    private LocalDate birth;

    @Builder
    public PersonalInfo(Gender gender, LocalDate birth) {
        this.gender = gender;
        this.birth = birth;
    }
}
