package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;

import de.fuberlin.hcc.questionnaires.model.Answer;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE_AND_TEXT")
public class MultipleChoiceAndTextAnswer extends Answer {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    List<ChoiceWithTextAnswer> selectedChoicesWithAnswers;

    //hibernate
    public MultipleChoiceAndTextAnswer() {
    }

    public MultipleChoiceAndTextAnswer(long questionId) {
        super(questionId);
        this.selectedChoicesWithAnswers = new ArrayList<>();
    }

    public boolean isSelected(long id) {
        return selectedChoicesWithAnswers.stream().map(ChoiceWithTextAnswer::getChoiceWithTextId).anyMatch((l) -> l == id);
    }

    public String getTextFor(long id) {
        for (ChoiceWithTextAnswer choice : selectedChoicesWithAnswers) {
            if (choice.getChoiceWithTextId() == id) {
                return choice.getAnswerText();
            }
        }
        return null;
    }

    public void addText(long choiceId, String answerText) {
        System.out.println("Add Text: " + answerText + " to choiceid: " + choiceId);
        selectedChoicesWithAnswers.add(new ChoiceWithTextAnswer(choiceId, answerText));
    }
}

