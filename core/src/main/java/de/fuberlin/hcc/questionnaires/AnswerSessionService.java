package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.Answer;
import de.fuberlin.hcc.questionnaires.model.AnswerSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AnswerSessionService {
    private final AnswerSessionRepository repository;

    @Autowired
    public AnswerSessionService(AnswerSessionRepository repository) {
        this.repository = repository;
    }

    public void saveAnswers(long questionnaireId, long userId, long contextKey, List<Answer> newAnswers) {
        final AnswerSession oldAnswers = repository.findByQuestionnaireIdAndUserIdAndContextKey(questionnaireId, userId, contextKey);
        final AnswerSession toSave = new AnswerSession(questionnaireId, userId, contextKey, newAnswers);
        if (oldAnswers != null) {
            repository.delete(oldAnswers.getId());
            toSave.setId(oldAnswers.getId());
        }
        repository.save(toSave);
    }


}
