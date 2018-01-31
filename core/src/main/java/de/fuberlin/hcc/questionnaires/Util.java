package de.fuberlin.hcc.questionnaires;

import de.fuberlin.hcc.questionnaires.model.Answer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestionWithAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlockAnswer;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlockWithAnswer;
import de.fuberlin.hcc.questionnaires.model.text.TextAnswer;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestionWithAnswer;

import java.util.HashMap;
import java.util.Map;

public class Util {


    public static Map<Long, Answer> parseAnswersFrom(Map<String, String[]> allRequestParams) {
        //Process all entities within the request params
        final Map<Long, Answer> cache = new HashMap<>();
        for (Map.Entry<String, String[]> entry : allRequestParams.entrySet()) {
            if (entry.getKey().startsWith("answer-")) {
                final String[] answerComponents = entry.getKey().split("-");
                final long questionId = Long.valueOf(answerComponents[1]);
                final String type = answerComponents[2];

                //TODO right now we can go through all the answers one by one. This could change if we have multiple-choice answers?

                if (TextQuestionWithAnswer.class.getSimpleName().equals(type)) {
                    cache.put(questionId, new TextAnswer(questionId, entry.getValue()[0]));
                } else if (SingleChoiceQuestionWithAnswer.class.getSimpleName().equals(type)) {
                    if (entry.getValue().length == 1) {
                        cache.put(questionId, new SingleChoiceAnswer(questionId, Long.valueOf(entry.getValue()[0])));
                    }
                } else if (RatingBlockWithAnswer.class.getSimpleName().equals(type)) {
                    Answer answer = cache.computeIfAbsent(questionId, RatingBlockAnswer::new);
                    ((RatingBlockAnswer) answer).addChoice(Long.valueOf(entry.getValue()[0]));
                } else {
                    throw new IllegalStateException("Found illegal entry: " + entry);
                }
            }
        }
        return cache;
    }
}
