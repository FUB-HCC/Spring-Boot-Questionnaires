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
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;


@RunWith(SpringRunner.class)
@DataJpaTest
public class QuestionnaireApplicationTest {

    @Test
    public void contextLoads() {
    }


    @Autowired
    private QuestionnaireRepository questionnaireRepository;

    @Autowired
    private QuestionnaireWithAnswersService questionnaireWithAnswersService;

    @Autowired
    private AnswerSessionService answerSessionService;

    @Test
    public void roundTripWithTestQuestionnaireWorks() {
        Questionnaire result = new Questionnaire();
        final Question q1 = new TextQuestion("How does it work?");
        final Question q2 = new SingleChoiceQuestion(
                "If you could buy such a product, how likely would you do so?",
                Arrays.asList("Definitely not buy",
                        "probably not buy",
                        "might or might not buy",
                        "probably buy",
                        "definitely buy"));
        q2.setAdditionalInfo("Think of your own consumer behaviour");
        final Question q3 = new RatingBlock(
                "How would you rate the following items?",
                Arrays.asList("Very low", "Below average", "Average", "Above Average", "Very high"),
                Arrays.asList("Novelty of the idea",
                        "Value of the idea",
                        "Feasibility of the idea",
                        "Elaboration of the idea")
        );
        result.add(q1);
        result.add(q2);
        result.add(q3);
        result = questionnaireRepository.save(result);

        //get the questionnaire
        //TODO optionalö
        final Questionnaire fromDb = questionnaireRepository.findById(result.getId()).get();
        assertThat(fromDb).isNotNull();

        long userId = 1L;
        long contextKey = 1L;

        final QuestionnaireWithAnswers questionnaireWithAnswers = questionnaireWithAnswersService.addAnswers(fromDb, userId, contextKey);
        final List<QuestionWithAnswer> questions = questionnaireWithAnswers.getQuestions();
        final QuestionWithAnswer qa1 = questions.get(0);
        assertThat(qa1).isInstanceOf(TextQuestionWithAnswer.class);
        assertThat(qa1.isAnswered()).isFalse();
        assertThat(qa1.getQuestionText()).isEqualTo("How does it work?");
        assertThat(qa1.getAdditionalInfo()).isNull();

        final QuestionWithAnswer qa2 = questions.get(1);
        assertThat(qa2).isInstanceOf(SingleChoiceQuestionWithAnswer.class);
        assertThat(qa2.isAnswered()).isFalse();
        assertThat(qa2.getQuestionText()).isEqualTo("If you could buy such a product, how likely would you do so?");
        assertThat(qa2.getAdditionalInfo()).isEqualTo("Think of your own consumer behaviour");
        assertThat(((SingleChoiceQuestionWithAnswer) qa2).getChoices()).hasSize(5);

        final QuestionWithAnswer qa3 = questions.get(2);
        assertThat(qa3).isInstanceOf(RatingBlockWithAnswer.class);
        assertThat(qa3.isAnswered()).isFalse();
        assertThat(qa3.getQuestionText()).isEqualTo("How would you rate the following items?");
        assertThat(qa3.getAdditionalInfo()).isNull();


        //Save answers
        List<Answer> mockAnswers = new ArrayList<>();
        mockAnswers.add(new TextAnswer(q1.getId(), "It doesn't"));
        mockAnswers.add(new SingleChoiceAnswer(q2.getId(), ((SingleChoiceQuestionWithAnswer) qa2).getChoices().get(1).getId()));
        mockAnswers.add(new RatingBlockAnswer(q3.getId()));
        answerSessionService.saveAnswers(fromDb.getId(), userId, contextKey, mockAnswers);

        //After Answering:
        final QuestionnaireWithAnswers afterAnswering = questionnaireWithAnswersService.addAnswers(fromDb, userId, contextKey);
        final List<QuestionWithAnswer> questionsAfterAnswering = afterAnswering.getQuestions();
        final QuestionWithAnswer qaa1 = questionsAfterAnswering.get(0);
        assertThat(qaa1).isInstanceOf(TextQuestionWithAnswer.class);
        assertThat(qaa1.isAnswered()).isTrue();
        assertThat(qaa1.getQuestionText()).isEqualTo("How does it work?");
        assertThat(((TextQuestionWithAnswer) qaa1).getAnswerText()).isEqualTo("It doesn't");
    }

    //TODO statistics

    //TODO overwrite with contextKey
}
