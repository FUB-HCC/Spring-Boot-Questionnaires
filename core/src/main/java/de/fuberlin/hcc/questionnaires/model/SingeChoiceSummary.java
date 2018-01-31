package de.fuberlin.hcc.questionnaires.model;

import java.util.List;

public class SingeChoiceSummary extends  QuestionsSummary {



    private String question;
    private RatingSummary rating;

    public SingeChoiceSummary(String question, RatingSummary rating){
        this.question = question;
        this.rating = rating;

    }


    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public RatingSummary getRating() {
        return rating;
    }

    public void setRating(RatingSummary rating) {
        this.rating = rating;
    }
}
