package de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock;

import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Entity
public class ColumnLabel implements Comparable<ColumnLabel> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @NotNull
    @Column(name = "column_label_order")
    private int order;

    @NotNull
    @NotEmpty
    @Size(max = 75)
    @Column(name = "label", length = 75, nullable = false)
    private String label;

    //hibernate
    public ColumnLabel() {
    }

    ColumnLabel(int order, String label) {
        this.order = order;
        this.label = label;
    }

    public int getOrder() {
        return order;
    }

    public String getLabel() {
        return label;
    }

    @Override
    public int compareTo(ColumnLabel that) {
        return this.order - that.order;
    }

}

