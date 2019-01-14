package de.fuberlin.hcc.questionnaires.model.singlechoice;


import de.fuberlin.hcc.questionnaires.model.QuestionWithAnswer;

import java.util.ArrayList;
import java.util.List;

public class SingleChoiceQuestionWithAnswer extends QuestionWithAnswer {
    private final List<ChoiceWithAnswer> choices;
    private final boolean hasAnswer;

    public SingleChoiceQuestionWithAnswer(SingleChoiceQuestion question, SingleChoiceAnswer answer) {
        super(question);
        final long selectedChoiceID;

        choices = new ArrayList<>(question.getAvailableChoices().size());
        hasAnswer = answer != null;
        if (hasAnswer) {
            selectedChoiceID = answer.getSelectedChoiceId();
        } else {
            selectedChoiceID = -1;
        }

        for (Choice choice : question.getAvailableChoices()) {
            choices.add(new ChoiceWithAnswer(choice.getId(), choice.getLabel(), choice.getId() == selectedChoiceID));
        }
    }

    public List<ChoiceWithAnswer> getChoices() {
        return choices;
    }

    @Override
    public boolean isAnswered() {
        return hasAnswer;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
