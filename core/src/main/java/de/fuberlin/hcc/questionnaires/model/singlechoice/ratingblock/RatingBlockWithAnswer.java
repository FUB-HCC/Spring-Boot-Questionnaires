package de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock;


import de.fuberlin.hcc.questionnaires.model.QuestionWithAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ChoiceWithAnswer;

import java.util.ArrayList;
import java.util.List;

public class RatingBlockWithAnswer extends QuestionWithAnswer {

    private final List<ColumnLabel> columnLabels;
    private final List<ChoiceRowWithAnswer> rows;
    private final boolean hasAnswer;

    public RatingBlockWithAnswer(RatingBlock question, RatingBlockAnswer answer) {
        super(question);
        hasAnswer = answer != null;
        columnLabels = question.getColumnLabels();
        rows = new ArrayList<>();
        for (ChoiceRow row : question.getChoiceRows()) {
            final ChoiceRowWithAnswer toAdd = new ChoiceRowWithAnswer(row);
            for (Choice choice : row.getAvailableChoices()) {
                final boolean isSelected = (answer != null) && (answer.getSelectedChoices().contains(choice.getId()));

                toAdd.addChoice(new ChoiceWithAnswer(choice.getId(), choice.getLabel(), isSelected));
            }
            rows.add(toAdd);
        }
    }

    @Override
    public boolean isAnswered() {
        return hasAnswer;
    }

    public List<ColumnLabel> getColumnLabels() {
        return columnLabels;
    }

    public List<ChoiceRowWithAnswer> getRows() {
        return rows;
    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }


}
