package de.fuberlin.hcc.questionnaires.model;


import java.util.Map;


public class RatingSummary {


    private long questionId;
    private int avg;
    private String label;
    private int numRating;
    private Map<Integer, String> ratingValues;

    public RatingSummary(long questionId, int avg, String label, int numRating) {
        this.questionId = questionId;
        this.avg = avg;
        this.label = label;
        this.numRating = numRating;
    }


    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    public int getNumRating() {
        return numRating;
    }

    public void setNumRating(int numRating) {
        this.numRating = numRating;
    }


    public int getAvg() {
        return avg;
    }

    public void setAvg(int avg) {
        this.avg = avg;
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }


    public void setRatingValues(Map<Integer, String> ratingsValues) {
        this.ratingValues = ratingsValues;
    }

    public String getValueLabel() {
        String value = ratingValues.get(avg);
        if (value == null || value == "") {
            value = " - ";
        }
        return value;
    }


}
