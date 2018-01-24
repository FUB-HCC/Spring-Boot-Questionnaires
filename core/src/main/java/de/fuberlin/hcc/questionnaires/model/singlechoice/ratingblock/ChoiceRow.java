package de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock;


import org.hibernate.annotations.SortNatural;
import org.hibernate.validator.constraints.NotEmpty;
import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
public class ChoiceRow {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @NotEmpty
    @Size(max = 75)
    @Column(name = "label", length = 75, nullable = false)
    private String label;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @SortNatural
    @OrderBy("value ASC")
    private List<Choice> availableChoices;

    //hibernate
    public ChoiceRow() {
    }

    ChoiceRow(String label) {
        this.label = label;
        this.availableChoices = new ArrayList<>();
    }

    public List<Choice> getAvailableChoices() {
        return availableChoices;
    }

    public String getLabel() {
        return label;
    }

    public void add(Choice choice) {
        this.availableChoices.add(choice);
    }

    public long getId() {
        return id;
    }
}
