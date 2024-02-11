package com.justsayit.infra.s3.dto;

import lombok.Getter;

@Getter
public class ProfileImgInfo {

    private String url;

    private ProfileImgInfo(String url) {
        this.url = url;
    }

    /**
     * 특정 프로필 이미지 주소를 담고 있는 객체를 반환한다
     * @param url 업로드한 이미지 주소
     * @return
     */
    public static ProfileImgInfo of(String url) {
        return new ProfileImgInfo(url);
    }

    /**
     * 기본 프로필 이미지 주소를 담고 있는 객체를 반환한다
     * @return
     */
    public static ProfileImgInfo ofDefault() {
        return new ProfileImgInfo("https://jsi-bucket.s3.ap-northeast-2.amazonaws.com/default_profile.png");
    }
}
