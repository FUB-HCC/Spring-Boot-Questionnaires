package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.AnswerSession;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface AnswerSessionRepository extends CrudRepository<AnswerSession, Long> {


    AnswerSession findByQuestionnaireIdAndUserIdAndContextKey(long questionnaireId, long userId, long contextKey);

    Iterable<AnswerSession> findByContextKey(long contextKey);
}


