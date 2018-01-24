package de.fuberlin.hcc.questionnaires.model.singlechoice;



import de.fuberlin.hcc.questionnaires.model.Question;
import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("SINGLE_CHOICE")
public class SingleChoiceQuestion extends Question {

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @SortNatural
    @OrderBy("value ASC")
    private List<Choice> availableChoices;

    //hibernate
    public SingleChoiceQuestion() {
        super();
    }

    public SingleChoiceQuestion(String questionText, List<String> answerOptions) {
        super(questionText);
        availableChoices = new ArrayList<>(answerOptions.size());
        for (int i = 0; i < answerOptions.size(); i++) {
            //Values are one based for better evaluation
            //TODO this doesn't work if the question is not saved yet :/
            availableChoices.add(new Choice(super.getId(), (i + 1), answerOptions.get(i)));
        }
    }

    public List<Choice> getAvailableChoices() {
        return availableChoices;
    }

    @Override
    public String toString() {
        return "SingleChoiceQuestion{" +
                "availableChoices=" + availableChoices +
                '}';
    }
}
