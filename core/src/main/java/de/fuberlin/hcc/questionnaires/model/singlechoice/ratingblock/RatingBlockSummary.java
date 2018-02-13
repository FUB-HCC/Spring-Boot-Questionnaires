package de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock;


import de.fuberlin.hcc.questionnaires.model.ChoiceRowWithSummary;
import de.fuberlin.hcc.questionnaires.model.Question;
import de.fuberlin.hcc.questionnaires.model.QuestionSummary;

import java.util.ArrayList;
import java.util.List;

public class RatingBlockSummary extends QuestionSummary {
    private final List<ChoiceRowWithSummary> choiceRowWithSummaries = new ArrayList<>();

    public RatingBlockSummary(Question question) {
        super(question);
    }

    public void add(ChoiceRowWithSummary choiceRowWithSummary) {
        choiceRowWithSummaries.add(choiceRowWithSummary);
    }

    public List<ChoiceRowWithSummary> getChoiceRowWithSummaries() {
        return choiceRowWithSummaries;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }
}
