package de.fuberlin.hcc.questionnaires;



import de.fuberlin.hcc.questionnaires.model.Answer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerService {


    private final AnswerRepository repository;

    @Autowired
    public AnswerService(AnswerRepository repository) {
        this.repository = repository;
    }


    public List<Answer> getAnswersByQuestionIdAndContextId(long qId, long contextId){
        return repository.getAnswersByQuestionIdAndContextId(qId,contextId);
    }
}
