package de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock;


import de.fuberlin.hcc.questionnaires.model.Answer;

import javax.persistence.DiscriminatorValue;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("RATING_BLOCK")
public class RatingBlockAnswer extends Answer {
    @ElementCollection
    private List<Long> selectedChoices;

    //hibernate
    public RatingBlockAnswer() {
    }

    public RatingBlockAnswer(long questionId) {
        super(questionId);
        selectedChoices = new ArrayList<>();
    }

    public RatingBlockAnswer(long questionId, List<Long> selectedChoices) {
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
