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
public class Feeling {

    @Transient
    private static final Long ZERO = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeling_id")
    private Long id;

    @Column(name = "empathy_count")
    private Long count;

    @Column(name = "is_selected")
    private boolean selected;

    public Feeling(boolean isSelected) {
        this(ZERO, isSelected);
    }

    private Feeling(Long count, boolean isSelected) {
        this.count = count;
        this.selected = isSelected;
    }
}
