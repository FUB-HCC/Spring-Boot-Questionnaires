package de.fuberlin.hcc.questionnaires.model;


import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

/**
 * https://stackoverflow.com/questions/1373294/inheritance-in-hibernate-annotations
 */
@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "question_type",
        discriminatorType = DiscriminatorType.STRING)



public abstract class Question implements Comparable<Question> {
    private static final int BEFORE = -1;
    private static final int AFTER = 1;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    long id;

    @NotNull
    @Column(name = "question_order")
    private int order;

    @NotNull
    @NotEmpty
    @Size(max = 2048)
    @Column(name = "question_text", length = 2048, nullable = false)
    private String questionText;

    @Size(max = 2048)
    @Column(name = "additional_info", length = 2048)
    private String additionalInfo;
/*
    @NotNull
    @Column(name = "question_type")
    private String questionType;
*/

    public Question() {
    }
/*
    public String  getQuestionType(){
        return questionType;
    }
    public void setQuestionType(String quastionType ){
       this.questionType = quastionType;
    }

   */
    public Question(String questionText) {
        this.questionText = questionText;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public String getQuestionText() {
        return questionText;
    }

    public String getAdditionalInfo() {
        return additionalInfo;
    }

    public void setAdditionalInfo(String additionalInfo) {
        this.additionalInfo = additionalInfo;
    }

    public long getId() {
        return id;
    }

    //comparable interface
    @Override
    public int compareTo(Question that) {
        if (that == null) {
            return BEFORE;
        }
        return this.getOrder() - that.getOrder();
    }

    @Override
    public String toString() {
        return "Question{" +
                "id=" + id +
                ", order=" + order +
                ", questionText='" + questionText + '\'' +
                ", additionalInfo='" + additionalInfo + '\'' +
                '}';
    }
}
