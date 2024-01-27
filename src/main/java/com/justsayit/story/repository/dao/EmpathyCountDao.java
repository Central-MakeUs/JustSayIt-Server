package com.justsayit.story.repository.dao;

import com.justsayit.story.domain.Emotion;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class EmpathyCountDao {

    private Emotion type;
    private Long count;

    @QueryProjection
    public EmpathyCountDao(Emotion type, Long count) {
        this.type = type;
        this.count = count;
    }
}
