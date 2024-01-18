package com.justsayit.story.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.security.core.parameters.P;

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

    public void addStory(Story story) {
        this.story = story;
        story.getPhotoList().add(this);
    }
}
