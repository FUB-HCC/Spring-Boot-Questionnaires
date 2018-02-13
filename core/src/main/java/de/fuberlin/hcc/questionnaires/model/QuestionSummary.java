package de.fuberlin.hcc.questionnaires.model;

public abstract class QuestionSummary {
    private final long id;
    private final String questionText;
    private final String additionalInfo;

    public QuestionSummary(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
        this.additionalInfo = question.getAdditionalInfo();
    }

    public abstract String getType();

    public String getQuestionText() {
        return questionText;
    }
}
