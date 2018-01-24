package de.fuberlin.hcc.questionnaires.model.text;


import de.fuberlin.hcc.questionnaires.model.Answer;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.validation.constraints.Size;

@Entity
@DiscriminatorValue("TEXT")
public class TextAnswer extends Answer {

    @Size(max = 2048)
    @Column(name = "answer_text", length = 2048)
    private String answerText;

    //hibernate
    public TextAnswer() {
    }

    public TextAnswer(long questionId, String answerText) {
        super(questionId);
        this.answerText = answerText;
    }

    public String getAnswerText() {
        return answerText;
    }
}
