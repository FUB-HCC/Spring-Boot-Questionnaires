package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.*;
import de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext.MultipleChoiceAndTextQuestion;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestion;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlock;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestion;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestionSummary;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionnaireSummaryService {
    private static final Logger log = LoggerFactory.getLogger(QuestionnaireSummaryService.class);

    private final RatingSummaryService ratingSummaryService;
    private final AnswerService answerService;

    @Autowired
    public QuestionnaireSummaryService(RatingSummaryService ratingSummaryService, AnswerService answerService) {
        this.ratingSummaryService = ratingSummaryService;
        this.answerService = answerService;
    }

    public QuestionnaireSummary getSummaryFor(Questionnaire questionnaire, long contextKey) {
        final QuestionnaireSummary result = new QuestionnaireSummary(questionnaire.getId());

        for (Question question : questionnaire.getQuestions()) {
            if (question instanceof RatingBlock) {
                result.add(ratingSummaryService.getSummaryForRatingBlock((RatingBlock) question, questionnaire.getId(), contextKey));
            } else if (question instanceof SingleChoiceQuestion) {
                result.add(ratingSummaryService.getSummaryForSingleChoiceQuestion((SingleChoiceQuestion) question, questionnaire.getId(), contextKey));
            } else if (question instanceof TextQuestion) {
                result.add(generateTextQuestionSummary((TextQuestion) question, contextKey));
            } else if (question instanceof MultipleChoiceAndTextQuestion) {
                result.add(ratingSummaryService.generateMultipleChoiceAndTextQuestionSummary((MultipleChoiceAndTextQuestion) question, contextKey));
            } else {
                log.error("Skipping unknown question type in summary: " + question);
            }
        }
        return result;
    }

    private QuestionSummary generateTextQuestionSummary(TextQuestion question, long contextKey) {
        final List<Answer> textQuestionAnswers = answerService.getAnswersByQuestionIdAndContextId(question.getId(), contextKey);
        return new TextQuestionSummary(question, textQuestionAnswers);
    }
}
