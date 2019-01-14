package de.fuberlin.hcc.questionnaires.model.multiplechoice.categories;

import de.fuberlin.hcc.questionnaires.model.QuestionWithAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ChoiceWithAnswer;

import java.util.ArrayList;
import java.util.List;

public class MultipleChoiceCategoriesQuestionWithAnswer extends QuestionWithAnswer {
    private final List<MultipleChoiceCategoryWithAnswer> categories;
    private final boolean hasAnswer;

    public MultipleChoiceCategoriesQuestionWithAnswer(MultipleChoiceCategoriesQuestion question, MultipleChoiceCategoriesAnswer answer) {
        super(question);
        hasAnswer = (answer != null);
        categories = new ArrayList<>();
        for (MultipleChoiceCategory category : question.getCategories()) {
            final MultipleChoiceCategoryWithAnswer toAdd = new MultipleChoiceCategoryWithAnswer(category);
            for (Choice choice : category.getAvailableChoices()) {
                final boolean isSelected = (answer != null) && (answer.getSelectedChoices().contains(choice.getId()));

                toAdd.addChoice(new ChoiceWithAnswer(choice.getId(), choice.getLabel(), isSelected));
            }
            categories.add(toAdd);
        }

    }

    @Override
    public String getType() {
        return this.getClass().getSimpleName();
    }

    @Override
    public boolean isAnswered() {
        return hasAnswer;
    }

    public List<MultipleChoiceCategoryWithAnswer> getCategories() {
        return categories;
    }
}
