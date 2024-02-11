package com.justsayit.member.domain;


import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import java.time.LocalDate;

@Getter
@Embeddable
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class PersonalInfo {

    @Enumerated(value = EnumType.STRING)
    private Gender gender;

    private LocalDate birth;

    @Builder
    public PersonalInfo(String gender, LocalDate birth) {
        this.gender = Gender.valueOf(gender);
        this.birth = birth;
    }
}
