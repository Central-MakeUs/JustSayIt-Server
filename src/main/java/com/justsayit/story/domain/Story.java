package com.justsayit.story.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import com.justsayit.story.exception.AlreadyDeletedStoryException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
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

    @Column(name = "UUID", nullable = false)
    private String UUID;

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Embedded
    @AttributeOverride(name = "bodyText", column = @Column(name = "body_text", nullable = false))
    private MainContent mainContent;

    @OneToMany(mappedBy = "story")
    private List<Photo> photoList = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "opened", column = @Column(name = "is_opened", nullable = false))
    @AttributeOverride(name = "anonymous", column = @Column(name = "is_anonymous", nullable = false))
    @AttributeOverride(name = "modified", column = @Column(name = "is_modified", nullable = false))
    private MetaInfo metaInfo;

    @Embedded
    @AttributeOverride(name = "angrySelected", column = @Column(name = "is_angry_selected", nullable = false))
    @AttributeOverride(name = "happinessSelected", column = @Column(name = "is_happiness_selected", nullable = false))
    @AttributeOverride(name = "sadnessSelected", column = @Column(name = "is_sadness_selected", nullable = false))
    @AttributeOverride(name = "surprisedSelected", column = @Column(name = "is_surprised_selected", nullable = false))
    private EmotionOfEmpathy emotionOfEmpathy;

    @Enumerated(EnumType.STRING)
    private StoryStatus status;

    private Story(Long memberId, MainContent mainContent, MetaInfo metaInfo, EmotionOfEmpathy emotionOfEmpathy) {
        this.UUID = createUUID();
        this.memberId = memberId;
        this.mainContent = mainContent;
        this.metaInfo = metaInfo;
        this.emotionOfEmpathy = emotionOfEmpathy;
        this.status = StoryStatus.POSTED;
    }

    private String createUUID() {
        StringBuilder sb = new StringBuilder();
        return sb.append(java.util.UUID.randomUUID())
                .append(LocalDateTime.now())
                .toString();
    }

    public static Story createStory(Long memberId, MainContent mainContent, MetaInfo metaInfo, EmotionOfEmpathy emotionOfEmpathy) {
        return new Story(memberId, mainContent, metaInfo, emotionOfEmpathy);
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

    public void remove() {
        if (this.status.equals(StoryStatus.DELETED)) {
            throw new AlreadyDeletedStoryException();
        }
        this.status = StoryStatus.DELETED;
    }
}
