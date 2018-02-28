package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.*;
import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestion;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceSummary;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.ChoiceRow;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.ColumnLabel;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlock;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlockSummary;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestion;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestionSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class QuestionnaireSummaryService {

    private final RatingRepository ratingRepository;
    private final AnswerService answerService;
    private final QuestionnaireRepository questionnaireRepository;

    @Autowired
    public QuestionnaireSummaryService(RatingRepository ratingRepository,
                                       AnswerService answerService,
                                       QuestionnaireRepository questionnaireRepository) {

        this.ratingRepository = ratingRepository;
        this.answerService = answerService;
        this.questionnaireRepository = questionnaireRepository;
    }

    public QuestionnaireSummary getSummaryFor(long  questionnaireId, long contextKey) {

        final Questionnaire questionnaire = questionnaireRepository.findOne(questionnaireId);
        final QuestionnaireSummary result = new QuestionnaireSummary(questionnaire.getId());

        for (Question question : questionnaire.getQuestions()) {
            if (question instanceof RatingBlock) {
                result.add(generateRatingBlockSummary((RatingBlock) question, contextKey));
            } else if (question instanceof SingleChoiceQuestion) {
                result.add(generateSingleChoiceSummary((SingleChoiceQuestion) question, contextKey));
            } else if (question instanceof TextQuestion) {
                result.add(generateTextQuestionSummary((TextQuestion) question, contextKey));
            }
        }
        return result;
    }

    private QuestionSummary generateTextQuestionSummary(TextQuestion question, long contextKey) {
        final List<Answer> textQuestionAnswers = answerService.getAnswersByQuestionIdAndContextId(question.getId(), contextKey);
        return new TextQuestionSummary(question, textQuestionAnswers);
    }

    private QuestionSummary generateRatingBlockSummary(RatingBlock ratingBlock, long contextKey) {
        final RatingBlockSummary summary = new RatingBlockSummary(ratingBlock);
        List<RatingSummary> ratingSummaries = ratingRepository.getAllRatingsForRatingBlockByQuestionIdAndContextKey(ratingBlock.getId(), contextKey);

        List<ChoiceRow> rows = ratingBlock.getChoiceRows();
        List<ColumnLabel> columnLabels = ratingBlock.getColumnLabels();
        Map<Integer, String> ratingValues = new HashMap<>();
        for (ColumnLabel columnLabel : columnLabels) {
            //TODO replace order with value
            ratingValues.put(columnLabel.getOrder() + 1, columnLabel.getLabel());
        }

        if (ratingSummaries.size() == 0) {
            for (ChoiceRow row : rows) {
                summary.add(new ChoiceRowWithSummary(row, null));
            }
        } else {
            int currentRatingIndex = 0;
            for (ChoiceRow row : rows) {
                if (currentRatingIndex >=ratingSummaries.size()) {
                    summary.add(new ChoiceRowWithSummary(row, null));
                } else {
                    final RatingSummary ratingSummaryForCurrentRow = ratingSummaries.get(currentRatingIndex);
                    if (row.getId() == ratingSummaryForCurrentRow.getQuestionId()) {
                        ratingSummaryForCurrentRow.setRatingValues(ratingValues);
                        summary.add(new ChoiceRowWithSummary(row, ratingSummaryForCurrentRow));
                        currentRatingIndex++;
                    } else {
                        summary.add(new ChoiceRowWithSummary(row, null));
                    }
                }
            }
        }
        return summary;
    }

    private QuestionSummary generateSingleChoiceSummary(SingleChoiceQuestion singleChoiceQuestion, long contextKey) {
        final List<Choice> availableChoices = singleChoiceQuestion.getAvailableChoices();

        final Map<Integer, String> ratingValuesSingleChoice = new HashMap<>();
        for (Choice choice : availableChoices) {
            ratingValuesSingleChoice.put(choice.getValue(), choice.getLabel());
        }
        //TODO this breaks if there are no single choice ratings for the question?
        RatingSummary ratingSummary = ratingRepository.getSingleChoiceRatingByQuestionIdAndContextKey(singleChoiceQuestion.getId(), contextKey);

        if (ratingSummary == null) {
            ratingSummary = new RatingSummary(singleChoiceQuestion.getId(), 0, "", 0);
        }
        ratingSummary.setRatingValues(ratingValuesSingleChoice);

        return new SingleChoiceSummary(singleChoiceQuestion, ratingSummary);
    }
}
