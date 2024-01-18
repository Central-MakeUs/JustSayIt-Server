package com.justsayit.story.domain;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter
@Entity
@Table(name = "FEELING")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public abstract class Feeling {

    @Transient
    private static final Long ZERO = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeling_id", nullable = false)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "story_id", nullable = false)
    private Story story;

    @Column(name = "empathy_count", nullable = false)
    private Long count;

    @Column(name = "is_selected", nullable = false)
    private boolean selected;

    public Feeling(Story story, boolean isSelected) {
        this(story, ZERO, isSelected);
    }

    private Feeling(Story story, Long count, boolean isSelected) {
        addStory(story);
        this.count = count;
        this.selected = isSelected;
    }

    private void addStory(Story story) {
        this.story = story;
        story.getFeelingsOfEmpathy().add(this);
    }
}
