package de.fuberlin.hcc.questionnaires;

import de.fuberlin.hcc.questionnaires.model.Answer;
import de.fuberlin.hcc.questionnaires.model.AnswerSession;
import de.fuberlin.hcc.questionnaires.model.ChoiceRowWithSummary;
import de.fuberlin.hcc.questionnaires.model.RatingSummary;
import de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext.MultipleChoiceAndTextQuestion;
import de.fuberlin.hcc.questionnaires.model.multiplechoice.choicewithtext.MultipleChoiceAndTextQuestionSummary;
import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestion;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceSummary;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class RatingSummaryService {

    private final AnswerSessionRepository answerSessionRepository;
    private final AnswerService answerService;

    @Autowired
    public RatingSummaryService(AnswerSessionRepository answerSessionRepository, AnswerService answerService) {
        this.answerSessionRepository = answerSessionRepository;
        this.answerService = answerService;
    }

    //TODO could have performance impact
    public RatingBlockSummary getSummaryForRatingBlock(RatingBlock ratingBlock, long questionnaireId, long contextKey) {
        final RatingBlockSummary result = new RatingBlockSummary(ratingBlock);

        //Step 1: Build a Mapping Between the Choice Rows and the Choice Ids
        final Map<Long, RatingSummary> choiceIdToRowMapping = new HashMap<>();
        final Map<Long, Choice> choiceCache = new HashMap<>();

        //Fill in the ColumnLabels
        final Map<Integer, String> columnLabels = new HashMap<>();
        for (ColumnLabel columnLabel : ratingBlock.getColumnLabels()) {
            //TODO replace order with value
            columnLabels.put(columnLabel.getOrder() + 1, columnLabel.getLabel());
        }

        for (ChoiceRow choiceRow : ratingBlock.getChoiceRows()) {
            final RatingSummary summaryForCurrentRow = new RatingSummary(ratingBlock.getId());
            summaryForCurrentRow.setRatingLabels(columnLabels);
            result.add(new ChoiceRowWithSummary(choiceRow, summaryForCurrentRow));
            for (Choice choice : choiceRow.getAvailableChoices()) {
                choiceIdToRowMapping.put(choice.getId(), summaryForCurrentRow);
                choiceCache.put(choice.getId(), choice);
            }
        }

        //Step 2: find all answer Sessions for the current contextKey and Question:
        final Iterable<AnswerSession> answerSessions = answerSessionRepository.findByQuestionnaireIdAndContextKey(questionnaireId, contextKey);
        final List<RatingBlockAnswer> answersForCurrentQuestionId = new ArrayList<>();

        for (AnswerSession answerSession : answerSessions) {
            for (Answer answer : answerSession.getAnswers()) {
                if (answer.getQuestionId() == ratingBlock.getId()) {
                    answersForCurrentQuestionId.add((RatingBlockAnswer) answer);
                }
            }
        }
        //Step 3: go through the answers and if the choice row matches, add the amount and increase count
        for (RatingBlockAnswer answer : answersForCurrentQuestionId) {
            for (Long choiceId : answer.getSelectedChoices()) {
                final Choice choice = choiceCache.get(choiceId);
                final RatingSummary currentSummary = choiceIdToRowMapping.get(choiceId);
                currentSummary.addValue(choice.getValue());
            }
        }

        //Step 4: calculate the averages (done in the "getAvg()" method in RatingSummary)
        return result;
    }

    public SingleChoiceSummary getSummaryForSingleChoiceQuestion(SingleChoiceQuestion question, long questionnaireId, long contextKey) {
        final RatingSummary singleChoiceRatingSummary = new RatingSummary(question.getId());
        final SingleChoiceSummary result = new SingleChoiceSummary(question, singleChoiceRatingSummary);

        final Map<Long, Choice> choiceCache = new HashMap<>();
        //Fill in the column Labels
        final Map<Integer, String> columnLabels = new HashMap<>();
        for (Choice choice : question.getAvailableChoices()) {
            columnLabels.put(choice.getValue(), choice.getLabel());
            choiceCache.put(choice.getId(), choice);
        }
        singleChoiceRatingSummary.setRatingLabels(columnLabels);

        // /Step 2: find all answer Sessions for the current contextKey and Question:
        final Iterable<AnswerSession> answerSessions = answerSessionRepository.findByQuestionnaireIdAndContextKey(questionnaireId, contextKey);
        final List<SingleChoiceAnswer> answersForCurrentQuestionId = new ArrayList<>();

        for (AnswerSession answerSession : answerSessions) {
            for (Answer answer : answerSession.getAnswers()) {
                if (answer.getQuestionId() == question.getId()) {
                    answersForCurrentQuestionId.add((SingleChoiceAnswer) answer);
                }
            }
        }
        //Step 3: go through the answers and if the choice row matches, add the amount and increase count
        for (SingleChoiceAnswer answer : answersForCurrentQuestionId) {
            long selectedChoiceId = answer.getSelectedChoiceId();
            Choice selectedChoice = choiceCache.get(selectedChoiceId);
            singleChoiceRatingSummary.addValue(selectedChoice.getValue());
        }

        return result;
    }

    public MultipleChoiceAndTextQuestionSummary generateMultipleChoiceAndTextQuestionSummary(MultipleChoiceAndTextQuestion question, long contextKey) {
        final List<Answer> multipleChoiceAndTextQuestionAnswers = answerService.getAnswersByQuestionIdAndContextId(question.getId(), contextKey);
        return new MultipleChoiceAndTextQuestionSummary(question, multipleChoiceAndTextQuestionAnswers);
    }
}
