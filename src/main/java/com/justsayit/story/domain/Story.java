package com.justsayit.story.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import com.justsayit.story.exception.EmptyMainContentException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Entity
@Table(name = "STORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Story extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;

    @Embedded
    @AttributeOverride(name = "title", column = @Column(name = "title", nullable = false))
    @AttributeOverride(name = "bodyText", column = @Column(name = "body_text", nullable = false))
    @AttributeOverride(name = "emotion", column = @Column(name = "emotion", nullable = false))
    private MainContent mainContent;

    @OneToMany(mappedBy = "story")
    private List<Photo> photoList = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "opened", column = @Column(name = "is_opened", nullable = false))
    @AttributeOverride(name = "anonymous", column = @Column(name = "is_anonymous", nullable = false))
    @AttributeOverride(name = "deleted", column = @Column(name = "is_deleted", nullable = false))
    private MetaInfo metaInfo;

    @OneToMany(mappedBy = "feeling_id")
    private List<Feeling> feelingsOfEmpathy = new ArrayList<>();

    public Story(MainContent mainContent, List<Photo> photoList, MetaInfo metaInfo, List<Feeling> feelingsOfEmpathy) {
        this.mainContent = mainContent;
        this.photoList = photoList;
        this.metaInfo = metaInfo;
        this.feelingsOfEmpathy = feelingsOfEmpathy;
    }

    public void changeMainContent(MainContent mainContent) {
        this.mainContent = mainContent;
    }

    public void changeMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    public void changePhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public void changeFeelingsOfEmpathy(List<Feeling> feelingsOfEmpathy) {
        this.feelingsOfEmpathy = feelingsOfEmpathy;
    }
}
