package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.Questionnaire;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionnaireRepository extends CrudRepository<Questionnaire, Long> {
}
