package de.fuberlin.hcc.questionnaires.model.multiplechoice.categories;

import de.fuberlin.hcc.questionnaires.model.Answer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE_AND_CATEGORIES")
public class MultipleChoiceCategoriesAnswer extends Answer {
    @ElementCollection
    private List<Long> selectedChoices;

    //hibernate
    public MultipleChoiceCategoriesAnswer() {
        this.selectedChoices = new ArrayList<>();
    }

    public MultipleChoiceCategoriesAnswer(long questionId) {
        super(questionId);
        this.selectedChoices = new ArrayList<>();
    }

    public MultipleChoiceCategoriesAnswer(long questionId, List<Long> selectedChoices) {
        super(questionId);
        this.selectedChoices = selectedChoices;
    }

    public void addChoice(long choice) {
        selectedChoices.add(choice);
    }

    public List<Long> getSelectedChoices() {
        return selectedChoices;
    }
}
