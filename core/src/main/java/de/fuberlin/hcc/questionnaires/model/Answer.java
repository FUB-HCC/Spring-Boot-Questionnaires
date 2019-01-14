package de.fuberlin.hcc.questionnaires.model;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "answer_type",
        discriminatorType = DiscriminatorType.STRING)


@SqlResultSetMapping(
        name = "ratingsresult",
        classes = {
                @ConstructorResult(
                        targetClass = RatingSummary.class,
                        columns = {
                                @ColumnResult(name = "qId", type = Long.class),
                                @ColumnResult(name = "avgRating", type = Integer.class),
                                @ColumnResult(name = "label", type = String.class),
                                @ColumnResult(name = "numRating", type = Integer.class)
                        })
        })

public abstract class Answer implements Comparable<Answer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @NotNull
    @Column(name = "question_id", nullable = false)
    private long questionId;

    //TODO is this empty constructor needed?
    public Answer() {
    }

    public Answer(long questionId) {
        this.questionId = questionId;
    }

    //comparable interface
    @Override
    public int compareTo(Answer that) {
        if (that == null) {
            return -1;
        }
        //TODO bad
        return (int) (this.questionId - that.getQuestionId());
    }

    public long getQuestionId() {
        return questionId;
    }

    public long getId() {
        return id;
    }
}
