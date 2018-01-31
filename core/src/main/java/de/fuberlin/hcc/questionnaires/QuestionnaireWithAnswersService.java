package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.*;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestion;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestionWithAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlock;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlockAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlockWithAnswer;
import de.fuberlin.hcc.questionnaires.model.text.TextAnswer;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestion;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestionWithAnswer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class QuestionnaireWithAnswersService {

    private final AnswerSessionRepository answerSessionRepository;


    @Autowired
    public QuestionnaireWithAnswersService(AnswerSessionRepository answerSessionRepository) {
        this.answerSessionRepository = answerSessionRepository;
    }

    public QuestionnaireWithAnswers addAnswers(Questionnaire questionnaire, long userId, long contextKey) {
        AnswerSession oldAnswers = answerSessionRepository.findByQuestionnaireIdAndUserIdAndContextKey(questionnaire.getId(), userId, contextKey);
        return buildQuestionnairewithAnswers(questionnaire, oldAnswers);
    }

    //TODO optimize/make nicer
    private QuestionnaireWithAnswers buildQuestionnairewithAnswers(Questionnaire questionnaire, AnswerSession oldAnswers) {
        final QuestionnaireWithAnswers result = new QuestionnaireWithAnswers(questionnaire.getId());
        final List<Answer> tmpAnswer;
        if (oldAnswers == null) {
            tmpAnswer = Collections.emptyList();
        } else {
            tmpAnswer = new ArrayList<>(oldAnswers.getAnswers());
        }

        for (final Question question : questionnaire.getQuestions()) {
            Answer foundOldAnswer = null;
            for (final Answer answer : tmpAnswer) {
                if (question.getId() == answer.getQuestionId()) {
                    foundOldAnswer = answer;
                    tmpAnswer.remove(answer);
                    break;
                }
            }
            result.add(buildQuestionWithAnswer(question, foundOldAnswer));
        }
        return result;
    }

    private QuestionWithAnswer buildQuestionWithAnswer(Question question, Answer answer) {
        QuestionWithAnswer result;
        if (question instanceof TextQuestion) {
            if (answer == null || answer instanceof TextAnswer) {
                result = new TextQuestionWithAnswer((TextQuestion) question, (TextAnswer) answer);
            } else {
                throw new IllegalStateException("Answer with ID " + answer.getId() + ". Is of wrong type. Expected " + TextAnswer.class);
            }
        } else if (question instanceof SingleChoiceQuestion) {
            if (answer == null || answer instanceof SingleChoiceAnswer) {
                result = new SingleChoiceQuestionWithAnswer((SingleChoiceQuestion) question, (SingleChoiceAnswer) answer);
            } else {
                throw new IllegalStateException("Answer with ID " + answer.getId() + ". Is of wrong type. Expected " + TextAnswer.class);
            }
        } else if (question instanceof RatingBlock) {
            if (answer == null || answer instanceof RatingBlockAnswer) {
                result = new RatingBlockWithAnswer((RatingBlock) question, (RatingBlockAnswer) answer);
            } else {
                throw new IllegalStateException("Answer with ID " + answer.getId() + ". Is of wrong type. Expected " + TextAnswer.class);
            }
        } else {
            throw new IllegalStateException("Found Question with type: " + question.getClass() + ". Cannot build QuestionWithAnswer out of it");
        }
        return result;
    }
}
