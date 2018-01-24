package de.fuberlin.hcc.questionnaires.model.text;

import static org.apache.commons.lang3.StringUtils.isNotBlank;
import de.fuberlin.hcc.questionnaires.model.QuestionWithAnswer;

public class TextQuestionWithAnswer extends QuestionWithAnswer {

    private final String answerText;

    public TextQuestionWithAnswer(TextQuestion question, TextAnswer answer) {
        super(question);
        this.answerText = (answer != null) ? answer.getAnswerText() : null;
    }

    public String getAnswerText() {
        return answerText;
    }

    @Override
    public boolean isAnswered() {
        return isNotBlank(answerText);
    }
}
