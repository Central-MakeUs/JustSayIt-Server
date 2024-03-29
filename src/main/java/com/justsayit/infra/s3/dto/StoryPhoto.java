package com.justsayit.infra.s3.dto;

import lombok.Getter;

@Getter
public class StoryPhoto {

    private String url;

    private StoryPhoto(String url) {
        this.url = url;
    }

    /**
     * 특정 프로필 이미지 주소를 담고 있는 객체를 반환한다
     * @param url 업로드한 이미지 주소
     * @return
     */
    public static StoryPhoto of(String url) {
        return new StoryPhoto(url);
    }
}
