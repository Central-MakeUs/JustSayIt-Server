package com.justsayit.story.repository.dto;

import com.justsayit.story.domain.Emotion;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class EmpathyCountDto {

    private Emotion type;
    private Long count;

    @QueryProjection
    public EmpathyCountDto(Emotion type, Long count) {
        this.type = type;
        this.count = count;
    }
}
