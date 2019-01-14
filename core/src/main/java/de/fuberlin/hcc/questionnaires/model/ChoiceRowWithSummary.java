package de.fuberlin.hcc.questionnaires.model;


import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.ChoiceRow;

public class ChoiceRowWithSummary {
    private final ChoiceRow choiceRow;
    private final RatingSummary ratingSummary;

    public ChoiceRowWithSummary(ChoiceRow choiceRow, RatingSummary ratingSummary) {
        this.choiceRow = choiceRow;
        this.ratingSummary = ratingSummary;
    }

    public String getAvgRating() {
        if (ratingSummary != null) {
            return ratingSummary.getValueLabel();
        }
        return "(No Ratings)";
    }

    public int getNumRating() {
        if (ratingSummary != null) {
            return ratingSummary.getNumRating();
        }
        return 0;
    }

    public String getRowLabel() {
        if (choiceRow != null) {
            return choiceRow.getLabel();
        }
        return "no label";
    }
}
