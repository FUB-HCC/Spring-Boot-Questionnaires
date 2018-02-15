package de.fuberlin.hcc.questionnaires;

import de.fuberlin.hcc.questionnaires.model.Answer;
import de.fuberlin.hcc.questionnaires.model.AnswerSession;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class EditService {


    private AnswerSessionService answerSessionService;

    public EditService(AnswerSessionService answerSessionService){
        this.answerSessionService = answerSessionService;
    }



    public AnswerSession update(Long  questionnaireId,
                                long userId, long contextId,
                                HttpServletRequest request){

        Map<String, String[]> allRequestParams = request.getParameterMap();
        final Map<Long, Answer> cache = Util.parseAnswersFrom(allRequestParams);
        List<Answer> answers = cache.entrySet().stream().map(Map.Entry::getValue).collect(Collectors.toList());
        AnswerSession answerSession = answerSessionService.saveAnswers(  questionnaireId, userId,contextId, answers);
        return answerSession;
    }
}
