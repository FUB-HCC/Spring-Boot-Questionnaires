package de.fuberlin.hcc.questionnaires.model;

import org.hibernate.annotations.SortNatural;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
public class Questionnaire implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;


    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @SortNatural
    @OrderBy("question_order ASC")
    private List<Question> questions = new ArrayList<>();

    @Column(name = "heading", length = 2_500, nullable = true)
    private String heading;

    public void add(Question question) {
        question.setOrder(questions.size() + 1);
        questions.add(question);
    }

    public long getId() {
        return id;
    }

    public int size() {
        return questions.size();
    }

    public void setHeading(String heading) {
        this.heading = heading;
    }

    public String getHeading() {
        return heading;
    }

    public List<Question> getQuestions() {
        return questions;
    }

    @Override
    public String toString() {
        return "Questionnaire{" +
                "id=" + id +
                ", questions=" + questions +
                '}';
    }
}
