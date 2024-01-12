package com.justsayit.infra.s3.dto;

import lombok.Getter;

@Getter
public class ProfileImgInfo {

    private String url;

    public ProfileImgInfo(String url) {
        this.url = url;
    }
}
