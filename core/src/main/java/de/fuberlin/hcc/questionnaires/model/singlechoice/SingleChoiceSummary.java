package de.fuberlin.hcc.questionnaires.model.singlechoice;


import de.fuberlin.hcc.questionnaires.model.QuestionSummary;
import de.fuberlin.hcc.questionnaires.model.RatingSummary;

public class SingleChoiceSummary extends QuestionSummary {
    private final RatingSummary ratingSummary;

    public SingleChoiceSummary(SingleChoiceQuestion question, RatingSummary ratingSummary) {
        super(question);
        this.ratingSummary = ratingSummary;
    }

    public RatingSummary getRatingSummary() {
        return ratingSummary;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
