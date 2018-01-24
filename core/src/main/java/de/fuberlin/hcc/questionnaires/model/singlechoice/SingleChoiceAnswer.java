package de.fuberlin.hcc.questionnaires.model.singlechoice;


import de.fuberlin.hcc.questionnaires.model.Answer;

import javax.persistence.Column;
import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;

@Entity
@DiscriminatorValue("SINGLE_CHOICE")
public class SingleChoiceAnswer extends Answer {

    @Column(name = "selected_choice_id")
    private long selectedChoiceId;

    public SingleChoiceAnswer() {
    }

    public SingleChoiceAnswer(long questionId, long selectedChoiceId) {
        super(questionId);
        this.selectedChoiceId = selectedChoiceId;
    }

    public long getSelectedChoiceId() {
        return selectedChoiceId;
    }
}
