package de.fuberlin.hcc.questionnaires.model.multiplechoice.categories;

import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;
import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class MultipleChoiceCategory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @NotEmpty
    @Size(max = 500)
    @Column(name = "label", length = 500, nullable = false)
    private String label;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @SortNatural
    @OrderBy("value ASC")
    private List<Choice> availableChoices;

    //hibernate
    public MultipleChoiceCategory() {
        super();
    }

    public MultipleChoiceCategory(long questionId, String label, List<String> answerOptions) {
        this.label = label;
        availableChoices = new ArrayList<>(answerOptions.size());
        for (int i = 0; i < answerOptions.size(); i++) {
            //Values are one based for better evaluation
            //TODO this doesn't work if the question is not saved yet :/
            availableChoices.add(new Choice(questionId, (i + 1), answerOptions.get(i)));
        }
    }

    public String getLabel() {
        return label;
    }

    public List<Choice> getAvailableChoices() {
        return availableChoices;
    }

    public long getId() {
        return id;
    }
}
