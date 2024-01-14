package com.justsayit.member.controller.request;

import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginReq {

    private String nickname;
    private String gender;
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    private LocalDate birth;
    private String loginType;
    private String token;
}
