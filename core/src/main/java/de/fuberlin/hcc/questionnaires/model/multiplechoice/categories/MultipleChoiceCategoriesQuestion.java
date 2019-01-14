package de.fuberlin.hcc.questionnaires.model.multiplechoice.categories;

import de.fuberlin.hcc.questionnaires.model.Question;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("MULTIPLE_CHOICE_AND_CATEGORIES")
public class MultipleChoiceCategoriesQuestion extends Question {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    private List<MultipleChoiceCategory> availableCategories;

    //hibernate
    public MultipleChoiceCategoriesQuestion() {
        super();
    }

    public MultipleChoiceCategoriesQuestion(String questionText) {
        super(questionText);
        this.availableCategories = new ArrayList<>();
    }

    public void addCategory(MultipleChoiceCategory category) {
        this.availableCategories.add(category);
    }

    public List<MultipleChoiceCategory> getCategories() {
        return availableCategories;
    }

    public void addCategory(String label, List<String> answerOptions) {
        MultipleChoiceCategory category = new MultipleChoiceCategory(super.getId(), label, answerOptions);
        this.availableCategories.add(category);
    }
}
