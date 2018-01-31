package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.Questionnaire;
import de.fuberlin.hcc.questionnaires.model.QuestionnaireWithAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;

@Service
public class View{

    private final QuestionnaireWithAnswersService questionnaireWithAnswersService;
    private final QuestionnaireRepository  questionnaireRepository;

    @Autowired
    public View(QuestionnaireWithAnswersService  questionnaireWithAnswersService,
                QuestionnaireRepository questionnaireRepository){
            this.questionnaireWithAnswersService =  questionnaireWithAnswersService;
            this.questionnaireRepository = questionnaireRepository;
    }



    public QuestionnaireWithAnswers getQuestionnaireWithAnswers(long questionnaireId,
                                                                long contexKey,
                                                                long userId) {

        final Questionnaire  questionnaire = questionnaireRepository.findOne(questionnaireId);
        final QuestionnaireWithAnswers questionsAndAnswers =
                questionnaireWithAnswersService .addAnswers(questionnaire, userId, contexKey);
        return questionsAndAnswers;

    }


}
