package com.justsayit.story.domain;

import com.justsayit.story.domain.feeling.Angry;
import com.justsayit.story.domain.feeling.Happiness;
import com.justsayit.story.domain.feeling.Sadness;
import com.justsayit.story.domain.feeling.Surprised;
import com.justsayit.story.exception.InvalidFeelingException;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Getter
@Entity
@Table(name = "FEELING")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class FeelingsOfEmpathy {

    @Transient
    private static final Long ZERO = 0L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "feeling_id", nullable = false)
    private Long id;

    @OneToOne(mappedBy = "feelingsOfEmpathy")
    private Story story;

    @Embedded
    @AttributeOverride(name = "count", column = @Column(name = "angry_count", nullable = false))
    @AttributeOverride(name = "selected", column = @Column(name = "angry_is_selected", nullable = false))
    private Angry angry;

    @Embedded
    @AttributeOverride(name = "count", column = @Column(name = "happiness_count", nullable = false))
    @AttributeOverride(name = "selected", column = @Column(name = "happiness_is_selected", nullable = false))
    private Happiness happiness;

    @Embedded
    @AttributeOverride(name = "count", column = @Column(name = "sadness_count", nullable = false))
    @AttributeOverride(name = "selected", column = @Column(name = "sadness_is_selected", nullable = false))
    private Sadness sadness;

    @Embedded
    @AttributeOverride(name = "count", column = @Column(name = "surprised_count", nullable = false))
    @AttributeOverride(name = "selected", column = @Column(name = "surprised_is_selected", nullable = false))
    private Surprised surprised;

    private FeelingsOfEmpathy(Angry angry, Happiness happiness, Sadness sadness, Surprised surprised) {
        this.angry = angry;
        this.happiness = happiness;
        this.sadness = sadness;
        this.surprised = surprised;
    }

    public static FeelingsOfEmpathy of(List<String> feelings) {
        FeelingsOfEmpathy feelingsOfEmpathy = new FeelingsOfEmpathy(new Angry(false), new Happiness(false), new Sadness(false), new Surprised(false));
        for (String feeling : feelings) {
            if (feeling.equals("ANGRY")) {
                feelingsOfEmpathy.changeAngryStatus(new Angry(true));
            } else if (feeling.equals("SADNESS")) {
                feelingsOfEmpathy.changeSadnessStatus(new Sadness(true));
            } else if (feeling.equals("SURPRISED")) {
                feelingsOfEmpathy.changeSurprisedStatus(new Surprised(true));
            } else if (feeling.equals("HAPPINESS")) {
                feelingsOfEmpathy.changeHappinessStatus(new Happiness(true));
            } else {
                throw new InvalidFeelingException();
            }
        }
        return feelingsOfEmpathy;
    }

    public void changeFeelings(List<String> feelings) {
        for (String feeling : feelings) {
            if (feeling.equals("ANGRY")) {
                this.changeAngryStatus(new Angry(true));
            } else if (feeling.equals("SADNESS")) {
                this.changeSadnessStatus(new Sadness(true));
            } else if (feeling.equals("SURPRISED")) {
                this.changeSurprisedStatus(new Surprised(true));
            } else if (feeling.equals("HAPPINESS")) {
                this.changeHappinessStatus(new Happiness(true));
            } else {
                throw new InvalidFeelingException();
            }
        }
    }

    private void changeAngryStatus(Angry angry) {
        this.angry = angry;
    }

    private void changeHappinessStatus(Happiness happiness) {
        this.happiness = happiness;
    }

    private void changeSadnessStatus(Sadness sadness) {
        this.sadness = sadness;
    }

    private void changeSurprisedStatus(Surprised surprised) {
        this.surprised = surprised;
    }

    public int getTotalCount() {
        return angry.getCount() + happiness.getCount() + sadness.getCount() + surprised.getCount();
    }
}
