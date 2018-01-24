package de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ChoiceWithAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class ChoiceRowWithAnswer {

    private long id;
    private String rowLabel;
    private List<ChoiceWithAnswer> choices;

    ChoiceRowWithAnswer(ChoiceRow row) {
        this.rowLabel = row.getLabel();
        this.id = row.getId();
        this.choices = new ArrayList<>();
    }

    void addChoice(ChoiceWithAnswer choiceWithAnswer) {
        this.choices.add(choiceWithAnswer);
    }

    public String getRowLabel() {
        return rowLabel;
    }

    public List<ChoiceWithAnswer> getChoices() {
        return choices;
    }

    public List<ChoiceWithAnswer> getSelected(){
        List<ChoiceWithAnswer> choiceWithAnswers = getChoices();

        return choiceWithAnswers.stream().filter(s->s.isSelected()).collect(Collectors.toList());
    }


    public long getId() {
        return id;
    }
}
