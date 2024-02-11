package com.justsayit.story.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "PHOTO")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Photo extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "photo_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id")
    private Story story;

    @Column(name = "img_url", nullable = false)
    private String imgUrl;

    public static Photo createPhoto(Story story, String imgUrl) {
        return new Photo(story, imgUrl);
    }

    private Photo(Story story, String imgUrl) {
        addStory(story);
        this.imgUrl = imgUrl;
    }

    // 연관관계 편의 메서드
    private void addStory(Story story) {
        this.story = story;
        story.getPhotoList().add(this);
    }
}
