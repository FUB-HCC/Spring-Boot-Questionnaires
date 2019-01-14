package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;

import javax.persistence.*;

@Entity
public class ChoiceWithTextAnswer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    private long choiceWithTextId;
    private String answerText;

    //hibernate
    public ChoiceWithTextAnswer() {}

    public ChoiceWithTextAnswer(long choiceWithTextId, String answerText) {
        this.choiceWithTextId = choiceWithTextId;
        this.answerText = answerText;
    }

    public long getChoiceWithTextId() {
        return choiceWithTextId;
    }

    public String getAnswerText() {
        return answerText;
    }
}
