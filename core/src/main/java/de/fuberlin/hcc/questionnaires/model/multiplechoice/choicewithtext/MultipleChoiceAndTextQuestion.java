package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;


import de.fuberlin.hcc.questionnaires.model.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE_AND_TEXT")
public class MultipleChoiceAndTextQuestion extends Question {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<ChoiceWithText> choicesWithTexts;

    //hibernate
    public MultipleChoiceAndTextQuestion() {
        super();
    }

    public MultipleChoiceAndTextQuestion(String questionText, List<String> answerOptions) {
        super(questionText);
        choicesWithTexts = new ArrayList<>(answerOptions.size());
        for (int i = 0; i < answerOptions.size(); i++) {
            //TODO this doesn't work if the question is not saved yet :/
            choicesWithTexts.add(new ChoiceWithText(super.getId(), answerOptions.get(i)));
        }
    }

    public List<ChoiceWithText> getChoicesWithTexts() {
        return choicesWithTexts;
    }
}
