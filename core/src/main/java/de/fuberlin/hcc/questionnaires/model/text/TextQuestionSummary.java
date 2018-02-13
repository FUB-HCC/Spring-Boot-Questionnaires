package de.fuberlin.hcc.questionnaires.model.text;

import de.fuberlin.hcc.questionnaires.model.Answer;
import de.fuberlin.hcc.questionnaires.model.QuestionSummary;

import java.util.List;

public class TextQuestionSummary extends QuestionSummary {
    private final List<Answer> answers;
    
    public TextQuestionSummary(TextQuestion question, List<Answer> textQuestionAnswers) {
        super(question);
        this.answers = textQuestionAnswers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
