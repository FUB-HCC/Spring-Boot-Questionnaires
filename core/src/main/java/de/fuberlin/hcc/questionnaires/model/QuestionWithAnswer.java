package de.fuberlin.hcc.questionnaires.model;

public abstract class QuestionWithAnswer {
    private final long id;
    private final String questionText;
    private final String additionalInfo;

    public QuestionWithAnswer(Question question) {
        this.id = question.getId();
        this.questionText = question.getQuestionText();
        this.additionalInfo = question.getAdditionalInfo();
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public long getId() {
        return id;
    }

    public abstract String getType();

    public abstract boolean isAnswered();

}
