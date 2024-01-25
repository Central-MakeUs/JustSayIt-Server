package com.justsayit.story.domain;

import com.justsayit.core.entity.BaseJpaEntity;
import lombok.AccessLevel;
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

    @Column(name = "member_id", nullable = false)
    private Long memberId;

    @Embedded
    @AttributeOverride(name = "bodyText", column = @Column(name = "body_text", nullable = false))
    @AttributeOverride(name = "emotion", column = @Column(name = "emotion", nullable = false))
    private MainContent mainContent;

    @OneToMany(mappedBy = "story")
    private List<Photo> photoList = new ArrayList<>();

    @Embedded
    @AttributeOverride(name = "opened", column = @Column(name = "is_opened", nullable = false))
    @AttributeOverride(name = "anonymous", column = @Column(name = "is_anonymous", nullable = false))
    @AttributeOverride(name = "modified", column = @Column(name = "is_modified", nullable = false))
    private MetaInfo metaInfo;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "feeling_id")
    private FeelingsOfEmpathy feelingsOfEmpathy;

    @Enumerated(EnumType.STRING)
    private StoryStatus status;

    private Story(Long memberId, MainContent mainContent, MetaInfo metaInfo, FeelingsOfEmpathy feelingsOfEmpathy) {
        this.uuid = createUUID();
        this.memberId = memberId;
        this.mainContent = mainContent;
        this.metaInfo = metaInfo;
        this.feelingsOfEmpathy = feelingsOfEmpathy;
        this.status = StoryStatus.ACTIVE;
    }

    private String createUUID() {
        StringBuilder sb = new StringBuilder();
        return sb.append(UUID.randomUUID())
                .append(LocalDateTime.now())
                .toString();
    }

    public static Story createStory(Long memberId, MainContent mainContent, MetaInfo metaInfo, FeelingsOfEmpathy feelingsOfEmpathy) {
        return new Story(memberId, mainContent, metaInfo, feelingsOfEmpathy);
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
