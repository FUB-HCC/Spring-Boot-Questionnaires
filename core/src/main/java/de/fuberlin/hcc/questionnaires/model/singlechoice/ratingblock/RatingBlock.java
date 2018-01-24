package de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock;
import org.hibernate.annotations.SortNatural;
import de.fuberlin.hcc.questionnaires.model.Question;
import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@DiscriminatorValue("RATING_BLOCK")
public class RatingBlock extends Question {

    //TODO ordering
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //@SortNatural
    //@OrderBy("value ASC")
    private List<ChoiceRow> choiceRows;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @SortNatural
    @OrderBy("order ASC")
    private List<ColumnLabel> columnLabels;

    //hibernate
    public RatingBlock() {
        super();
    }

    public RatingBlock(String questionText, List<String> inputColumns, List<String> rowLabels) {
        super(questionText);
        columnLabels = new ArrayList<>(inputColumns.size());
        choiceRows = new ArrayList<>(rowLabels.size());

        //Fill up Column Labels
        for (int i = 0; i < inputColumns.size(); i++) {
            columnLabels.add(new ColumnLabel(i, inputColumns.get(i)));
        }

        //Fill up Rows:
        for (String rowLabel : rowLabels) {
            ChoiceRow row = new ChoiceRow(rowLabel);
            for (int i = 0; i < inputColumns.size(); i++) {
                //Values are one based for better evaluation
                //TODO this doesn't work if the question is not saved yet :/
                row.add(new Choice(super.getId(), (i + 1), rowLabel + ":" + inputColumns.get(i)));
            }
            choiceRows.add(row);
        }
    }

    public List<ChoiceRow> getChoiceRows() {
        return choiceRows;
    }

    public List<ColumnLabel> getColumnLabels() {
        return columnLabels;
    }



}
