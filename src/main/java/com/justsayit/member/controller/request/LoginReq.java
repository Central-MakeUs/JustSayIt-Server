package com.justsayit.member.controller.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class LoginReq {

    private String nickname;
    private String loginType;
    private String token;
}
