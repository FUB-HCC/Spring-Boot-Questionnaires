package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;

import de.fuberlin.hcc.questionnaires.model.QuestionWithAnswer;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceAndTextQuestionWithAnswer extends QuestionWithAnswer {
    private List<ChoiceWithTextWithAnswer> rows;
    private boolean hasAnswer;


    public MultipleChoiceAndTextQuestionWithAnswer(MultipleChoiceAndTextQuestion question, MultipleChoiceAndTextAnswer answer) {
        super(question);
        rows = new ArrayList<>();
        hasAnswer = false;
        for (ChoiceWithText choice : question.getChoicesWithTexts()) {
            if (answer != null) {
                final boolean isSelected = answer.isSelected(choice.getId());
                final String answerText = answer.getTextFor(choice.getId());
                rows.add(new ChoiceWithTextWithAnswer(choice, isSelected, answerText));
                hasAnswer = true;
            } else {
                rows.add(new ChoiceWithTextWithAnswer(choice, false, null));
            }
        }
    }

    @Override
    public String getType() {
        return MultipleChoiceAndTextQuestionWithAnswer.class.getSimpleName();
    }

    @Override
    public boolean isAnswered() {
        return hasAnswer;
    }

    public List<ChoiceWithTextWithAnswer> getRows() {
        return rows;
    }
}
