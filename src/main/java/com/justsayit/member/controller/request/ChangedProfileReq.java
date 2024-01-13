package com.justsayit.member.controller.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class ChangedProfileReq {

    private String nickname;
    private String profileImg;
}
