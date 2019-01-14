package de.fuberlin.hcc.questionnaires.model;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

/**
 * An Answer Session is the Data/Metadata Object for a Questionnaire filled
 * out by a user.
 * It consists of
 * - An ID
 * - The reference to questionnaire that was filled out
 * - A "contextKey" that determines, if a Session is appended or replaced
 * by a newer session for the same questionnaire
 * - A user id
 * - A list of answers given to the questions in the questionaire
 * - The submission timestamp of the answer session
 * This class should be sufficient to pre-fill a questionaire rendering
 * if a user has already answered a questionaire
 */

@Entity
public class AnswerSession {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "id")
    private long id;

    @Column(name = "questionnaire_id", nullable = false)
    private long questionnaireId;

    @Column(name = "user_id", nullable = false)
    private long userId;

    @Column(name = "context_key", nullable = false)
    private long contextKey;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private List<Answer> answers = new ArrayList<>();

    //hibernate
    public AnswerSession() {
    }

    public AnswerSession(long questionnaireId, long userId, long contextKey, List<Answer> answers) {
        this.questionnaireId = questionnaireId;
        this.userId = userId;
        this.contextKey = contextKey;
        this.answers = answers;
    }

    public List<Answer> getAnswers() {
        return answers;
    }

    public void addAnswer(Answer input) {
        answers.add(input);
    }

    public long getUserId() {
        return userId;
    }

    public long getContextKey() {
        return contextKey;
    }

    public long getQuestionnaireId() {
        return questionnaireId;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }
}
