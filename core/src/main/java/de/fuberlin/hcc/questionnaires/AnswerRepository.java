package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.Answer;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswerRepository extends CrudRepository<Answer, Long> {


    @Query(value = "Select * from ANSWER as A INNER JOIN ANSWER_SESSION_ANSWERS " +
            "as B ON A.ID = B.ANSWERS_ID INNER JOIN ANSWER_SESSION as C ON " +
            "B.ANSWER_SESSION_ID=C.ID WHERE A.QUESTION_ID= :qId and C.CONTEXT_KEY= :ckId", nativeQuery = true)
    public List<Answer> getAnswersByQuestionIdAndContextId(@Param("qId") long qId,
                                                           @Param("ckId") long contextKey);

}


