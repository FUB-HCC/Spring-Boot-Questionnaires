package de.fuberlin.hcc.questionnaires.model.singlechoice;


import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class Choice implements Comparable<Choice> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    //TODO brauch ich das?
    @Column(name = "question_id", nullable = false)
    private long questionId;

    @Column(name = "value", nullable = false)
    private int value;

    @Size(max = 75)
    @Column(name = "label", length = 75, nullable = true)
    private String label;

    public Choice() {
    }

    public Choice(long questionId, int value, String label) {
        this.questionId = questionId;
        this.value = value;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public int getValue() {
        return value;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int compareTo(Choice that) {
        return this.value - that.value;
    }

    @Override
    public String toString() {
        return "Choice{(" + value + "),'" + label + "'}";
    }
}
