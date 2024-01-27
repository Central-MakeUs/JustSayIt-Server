package com.justsayit.story.repository.dao;

import com.justsayit.story.domain.Feeling;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Getter;

@Getter
public class EmpathyCountDao {

    private Feeling type;
    private Long count;

    @QueryProjection
    public EmpathyCountDao(Feeling type, Long count) {
        this.type = type;
        this.count = count;
    }
}
