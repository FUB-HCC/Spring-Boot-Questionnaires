package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
public class ChoiceWithText {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    //TODO brauch ich das?
    @Column(name = "question_id", nullable = false)
    private long questionId;

    @Size(max = 500)
    @Column(name = "label", length = 500)
    private String label;

    //hibernate
    public ChoiceWithText() {
        super();
    }

    public ChoiceWithText(long questionId, String label) {
        this.questionId = questionId;
        this.label = label;
    }

    public long getId() {
        return id;
    }

    public long getQuestionId() {
        return questionId;
    }

    public String getLabel() {
        return label;
    }
}
