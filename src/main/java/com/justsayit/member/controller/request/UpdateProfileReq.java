package com.justsayit.member.controller.request;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class UpdateProfileReq {

    private String nickname;
    private String profileImg;
    private boolean defaultProfileImg;
}
