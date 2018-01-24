package de.fuberlin.hcc.questionnaires.model.text;



import de.fuberlin.hcc.questionnaires.model.Question;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("TEXT")
public class TextQuestion extends Question {

    //hibernate
    public TextQuestion() {
        super();
    }

    public TextQuestion(String questionText) {
        super(questionText);
    }

    @Override
    public String getQuestionText() {
        return super.getQuestionText();
    }

    @Override
    public String toString() {
        return super.toString();
    }
}
