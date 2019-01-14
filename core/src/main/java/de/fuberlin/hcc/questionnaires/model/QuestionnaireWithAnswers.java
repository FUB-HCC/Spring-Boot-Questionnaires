package de.fuberlin.hcc.questionnaires.model;

import java.util.ArrayList;
import java.util.List;


public class QuestionnaireWithAnswers {
    private final long id;
    private final String heading;
    private final List<QuestionWithAnswer> questionsWithAnswers = new ArrayList<>();

    public QuestionnaireWithAnswers(long id, String heading) {
        this.id = id;
        this.heading = heading;
    }

    public void add(QuestionWithAnswer questionWithAnswer) {
        questionsWithAnswers.add(questionWithAnswer);
    }

    public List<QuestionWithAnswer> getQuestions() {
        return questionsWithAnswers;
    }

    public long getId() {
        return id;
    }

    public String getHeading() {
        return heading;
    }
}
