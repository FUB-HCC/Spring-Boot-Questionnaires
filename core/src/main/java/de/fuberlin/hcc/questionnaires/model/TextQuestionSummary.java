package de.fuberlin.hcc.questionnaires.model;

import java.util.List;

public class TextQuestionSummary  extends  QuestionsSummary{


    private String question;
    private  List<Answer> answers;

    public TextQuestionSummary(String question, List<Answer> answers){
        this.question = question;
        this.answers= answers;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(List<Answer> answers) {
        this.answers = answers;
    }


}
