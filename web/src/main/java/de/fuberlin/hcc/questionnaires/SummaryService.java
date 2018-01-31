package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.*;
import de.fuberlin.hcc.questionnaires.model.singlechoice.Choice;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestion;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.ChoiceRow;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.ColumnLabel;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlock;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestion;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class SummaryService {

    private final QuestionnaireRepository  questionnaireRepository;
    private RatingRepository ratingRepository;
    private final AnswerService answerService;


    @Autowired
    public SummaryService(RatingRepository ratingRepository,
                          QuestionnaireRepository questionnaireRepository,
                          AnswerService answerService) {

        this.ratingRepository = ratingRepository;
        this.questionnaireRepository = questionnaireRepository;
        this.answerService = answerService;
    }



    public List<QuestionsSummary> getSummaries(long questionnaireId,
                                                         long contextKey,
                                                         long userId) {


        final Questionnaire quastionnarire = questionnaireRepository.findOne(questionnaireId);
        List<QuestionsSummary> questionsSummaries = new ArrayList<>();
        for (Question q : quastionnarire.getQuestions()) {
            if (q instanceof RatingBlock) {
                List<RatingSummary> ratingSummaries = ratingRepository.getAllRatingsForRatingBlockByQuestionIdAndContextKey(q.getId(), contextKey);
                RatingBlock ratingBlock = (RatingBlock) q;
                List<ChoiceRow> rows = ratingBlock.getChoiceRows();
                List<ColumnLabel> columnLabels = ratingBlock.getColumnLabels();
                Map<Integer, String> ratingValues = new HashMap<>();
                for (ColumnLabel columnLabel : columnLabels) {
                    //TODO replace order with value
                    ratingValues.put(columnLabel.getOrder() + 1, columnLabel.getLabel());
                }

                if (ratingSummaries.size() == 0) {
                    for (ChoiceRow row : rows) {
                        questionsSummaries.add(new ChoiceRowWithSummary(row, null));
                    }
                } else {
                    int currentRatingIndex = 0;
                    for (ChoiceRow row : rows) {
                        if (currentRatingIndex > ratingSummaries.size()) {
                            questionsSummaries.add(new ChoiceRowWithSummary(row, null));
                        } else {
                            final RatingSummary ratingSummaryForCurrentRow = ratingSummaries.get(currentRatingIndex);
                            if (row.getId() == ratingSummaryForCurrentRow.getQuestionId()) {
                                ratingSummaryForCurrentRow.setRatingValues(ratingValues);
                                questionsSummaries.add(new ChoiceRowWithSummary(row, ratingSummaryForCurrentRow));
                                currentRatingIndex++;
                            } else {
                                questionsSummaries.add(new ChoiceRowWithSummary(row, null));
                            }
                        }
                    }
                }
            } else if (q instanceof SingleChoiceQuestion) {
                final SingleChoiceQuestion singleChoiceQuestion = (SingleChoiceQuestion) q;
                final List<Choice> availableChoices = singleChoiceQuestion.getAvailableChoices();

                final Map<Integer, String> ratingValuesSingleChoice = new HashMap<>();
                for (Choice choice : availableChoices) {
                    ratingValuesSingleChoice.put(choice.getValue(), choice.getLabel());
                }

                RatingSummary ratingSummary = ratingRepository.getSingleChoiceRatingByQuestionIdAndContextKey(q.getId(), contextKey);

                if (ratingSummary == null) {
                    ratingSummary = new RatingSummary(q.getId(), 0, "", 0);
                }
                ratingSummary.setRatingValues(ratingValuesSingleChoice);
                questionsSummaries.add(new SingeChoiceSummary(q.getQuestionText(), ratingSummary));

            } else if (q instanceof TextQuestion) {
                List<Answer> textQuestionAnswers = answerService.getAnswersByQuestionIdAndContextId(q.getId(), contextKey);
                questionsSummaries.add(new TextQuestionSummary(q.getQuestionText(), textQuestionAnswers));

            }

        }
        return  questionsSummaries;
    }



    }


