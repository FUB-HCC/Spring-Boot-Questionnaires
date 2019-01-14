package de.fuberlin.hcc.questionnaires.model;


import java.util.Map;


public class RatingSummary {

    private long questionId;
    private int sum;
    private int numRating;
    private Map<Integer, String> ratingLabels;

    public RatingSummary(long questionId) {
        this.questionId = questionId;
        this.sum = 0;
        this.numRating = 0;
    }

    public void addValue(int value) {
        this.sum += value;
        this.numRating++;
    }

    public long getQuestionId() {
        return questionId;
    }

    public void setQuestionId(int questionId) {
        this.questionId = questionId;
    }


    public int getAvg() {
        if (numRating > 0) {
            return (sum / numRating);
        } else {
            return 0;
        }
    }

    public int getNumRating() {
        return numRating;
    }

    public void setRatingLabels(Map<Integer, String> ratingsValues) {
        this.ratingLabels = ratingsValues;
    }

    public String getValueLabel() {
        String value = null;
        if (ratingLabels != null) {
            value = ratingLabels.get(getAvg());
        }
        if (value == null || value.equals("")) {
            value = "No Ratings";
        }
        return value;
    }
}
