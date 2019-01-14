package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.Questionnaire;
import de.fuberlin.hcc.questionnaires.model.QuestionnaireWithAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ViewService {

    private final QuestionnaireWithAnswersService questionnaireWithAnswersService;
    private final QuestionnaireRepository  questionnaireRepository;

    @Autowired
    public ViewService(QuestionnaireWithAnswersService  questionnaireWithAnswersService,
                       QuestionnaireRepository questionnaireRepository){
            this.questionnaireWithAnswersService =  questionnaireWithAnswersService;
            this.questionnaireRepository = questionnaireRepository;
    }



    public QuestionnaireWithAnswers getQuestionnaireWithAnswers(long questionnaireId,
                                                                long contexKey,
                                                                long userId) {

        final Questionnaire  questionnaire = questionnaireRepository.findById(questionnaireId).get();
        final QuestionnaireWithAnswers questionsAndAnswers =
                questionnaireWithAnswersService .addAnswers(questionnaire, userId, contexKey);
        return questionsAndAnswers;

    }


}
