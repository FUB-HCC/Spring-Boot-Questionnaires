package de.fuberlin.hcc.questionnaires.model;

import java.util.ArrayList;
import java.util.List;

public class QuestionnaireSummary {
    private final long id;
    private List<QuestionSummary> summaryEntries = new ArrayList<>();

    public QuestionnaireSummary(long id) {
        this.id = id;
    }

    public void add(QuestionSummary entry) {
        summaryEntries.add(entry);
    }

    public List<QuestionSummary> getSummaryEntries() {
        return summaryEntries;
    }

    public long getId() {
        return id;
    }
}
