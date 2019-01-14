package de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext;

import de.fuberlin.hcc.questionnaires.model.Answer;
import de.fuberlin.hcc.questionnaires.model.QuestionSummary;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MultipleChoiceAndTextQuestionSummary extends QuestionSummary {
    private final Map<Long, ChoiceAndTextSummary> summaryMap;

    public MultipleChoiceAndTextQuestionSummary(MultipleChoiceAndTextQuestion question, List<Answer> multipleChoiceAndTextQuestionAnswers) {
        super(question);
        summaryMap = new HashMap<>();
        for (Answer answer : multipleChoiceAndTextQuestionAnswers) {
            MultipleChoiceAndTextAnswer castedAnswer = (MultipleChoiceAndTextAnswer) answer;
            for (ChoiceWithTextAnswer selectedChoiceWithTextAnswer : castedAnswer.selectedChoicesWithAnswers) {
                ChoiceAndTextSummary currentSummary = summaryMap.computeIfAbsent(selectedChoiceWithTextAnswer.getChoiceWithTextId(), ChoiceAndTextSummary::new);
                currentSummary.addText(selectedChoiceWithTextAnswer.getAnswerText());
            }
        }
        for (ChoiceWithText choice : question.getChoicesWithTexts()) {
            if (summaryMap.containsKey(choice.getId())) {
                ChoiceAndTextSummary choiceAndTextSummary = summaryMap.get(choice.getId());
                choiceAndTextSummary.setLabel(choice.getLabel());
            }
        }
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    public Collection<ChoiceAndTextSummary> getChoiceAndTextSummaries() {
        return summaryMap.values();
    }
}
