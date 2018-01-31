package de.fuberlin.hcc.questionnaires.model;


import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.ChoiceRow;

public class ChoiceRowWithSummary  extends QuestionsSummary {
    private final ChoiceRow choiceRow;
    private final RatingSummary ratingSummary;

    public ChoiceRowWithSummary(ChoiceRow choiceRow, RatingSummary ratingSummary) {
        this.choiceRow = choiceRow;
        this.ratingSummary = ratingSummary;
    }

    public RatingSummary getRatingSummary() {
        return ratingSummary;
    }

    public String getAvgRating() {
        if (ratingSummary != null) {
            return ratingSummary.getValueLabel();
        }
        return "-";
    }

    public Integer getNumRating() {
        if (ratingSummary != null) {
            return ratingSummary.getNumRating();
        }
        return 0;
    }

    public ChoiceRow getChoiceRow() {
        return choiceRow;
    }

    public String getRowLabel() {
        if (choiceRow != null) {
            return choiceRow.getLabel();
        }
        return "";
    }
}
