package de.fuberlin.hcc.questionnaires.model.multiplechoice.categories;

import de.fuberlin.hcc.questionnaires.model.singlechoice.ChoiceWithAnswer;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MultipleChoiceCategoryWithAnswer {
    private long id;
    private String label;
    private List<ChoiceWithAnswer> choices;

    public MultipleChoiceCategoryWithAnswer(MultipleChoiceCategory category) {
        this.id = category.getId();
        this.label = category.getLabel();
        this.choices = new ArrayList<>();
    }

    void addChoice(ChoiceWithAnswer choiceWithAnswer) {
        this.choices.add(choiceWithAnswer);
    }

    public String getLabel() {
        return label;
    }

    public List<ChoiceWithAnswer> getChoices() {
        return choices;
    }

    public List<ChoiceWithAnswer> getSelected() {
        List<ChoiceWithAnswer> choiceWithAnswers = getChoices();
        return choiceWithAnswers.stream().filter(ChoiceWithAnswer::isSelected).collect(Collectors.toList());
    }

    public long getId() {
        return id;
    }
}
