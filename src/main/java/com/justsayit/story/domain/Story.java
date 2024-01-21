package com.justsayit.story.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Getter
@Entity
@Table(name = "STORY")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Story extends BaseJpaEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "story_id")
    private Long id;

    @Column(name = "uuid", nullable = false)
    private String uuid;

    @Embedded
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

    @OneToOne
    @JoinColumn(name = "feeling_id")
    private FeelingsOfEmpathy feelingsOfEmpathy;

    @Builder
    private Story(MainContent mainContent, MetaInfo metaInfo, FeelingsOfEmpathy feelingsOfEmpathy) {
        this.mainContent = mainContent;
        this.metaInfo = metaInfo;
        this.feelingsOfEmpathy = feelingsOfEmpathy;
        this.uuid = createUUID();
    }

    private String createUUID() {
        StringBuilder sb = new StringBuilder();
        return sb.append(UUID.randomUUID())
                .append(LocalDateTime.now())
                .toString();
    }

    public void changeMainContent(MainContent mainContent) {
        this.mainContent = mainContent;
    }

    public void changePhotoList(List<Photo> photoList) {
        this.photoList = photoList;
    }

    public void changeMetaInfo(MetaInfo metaInfo) {
        this.metaInfo = metaInfo;
    }

    public void changeFeelingsOfEmpathy(FeelingsOfEmpathy feelingsOfEmpathy) {
        this.feelingsOfEmpathy = feelingsOfEmpathy;
    }
}
