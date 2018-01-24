package de.fuberlin.hcc.questionnaires;



import de.fuberlin.hcc.questionnaires.model.RatingSummary;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.persistence.*;
import java.util.List;


@Repository
public class RatingRepository {

    @Autowired
    private EntityManagerFactory entityManagerFactory;


    public List<RatingSummary> getAllRatingsForRatingBlockByQuestionIdAndContextKey(long questionnaireId, long contextKex) {
        EntityManager session = entityManagerFactory.createEntityManager();
        List<RatingSummary> ratingSummaries = (List<RatingSummary>) session.createNativeQuery("Select H.CHOICE_ROW_ID as qId, AVG(C.VALUE) as avgRating, COUNT(C.VALUE) as numRating, M.LABEL as label  " +
                "from ANSWER_SESSION as S  INNER JOIN  ANSWER_SESSION_ANSWERS as A " +
                "ON S.ID=A.ANSWER_SESSION_ID INNER JOIN ANSWER as B ON A.ANSWERS_ID=B.ID " +
                "INNER JOIN  RATING_BLOCK_ANSWER_SELECTED_CHOICES as R ON B.ID = R.RATING_BLOCK_ANSWER_ID " +
                "INNER JOIN CHOICE as C ON C.ID = R.SELECTED_CHOICES  INNER JOIN CHOICE_ROW_AVAILABLE_CHOICES as " +
                "H ON H.AVAILABLE_CHOICES_ID =  C.ID " +
                "INNER JOIN CHOICE_ROW as M ON M.ID = H.CHOICE_ROW_ID Where B.QUESTION_ID=:qId and S.CONTEXT_KEY=:ckId " +
                "GROUP BY H.CHOICE_ROW_ID ORDER BY H.CHOICE_ROW_ID", "ratingsresult")
                .setParameter("ckId", contextKex)
                .setParameter("qId", questionnaireId).getResultList();
        return ratingSummaries;

    }


    public RatingSummary getSingleChoiceRatingByQuestionIdAndContextKey(long qId, long ckId) {

        EntityManager session = entityManagerFactory.createEntityManager();

        List<RatingSummary> ratingSummaries = (List<RatingSummary>) session.createNativeQuery("Select B.QUESTION_ID as qId,  AVG(C.VALUE) as avgRating, COUNT(C.VALUE) as numRating, Q.QUESTION_TEXT as label " +
                "from ANSWER_SESSION as S INNER JOIN ANSWER_SESSION_ANSWERS as A on S.ID = A.ANSWER_SESSION_ID " +
                "INNER JOIN ANSWER as B on A.ANSWERS_ID=B.ID INNER JOIN QUESTION as Q ON B.QUESTION_ID = Q.ID INNER JOIN CHOICE as C " +
                "ON C.ID = B.SELECTED_CHOICE_ID WHERE B.QUESTION_ID=:qId and S.CONTEXT_KEY=:ckId GROUP BY Q.ID", "ratingsresult").setParameter("ckId", ckId)
                .setParameter("qId", qId).getResultList();

        if(ratingSummaries.size()!=0){
            return ratingSummaries.get(0);
        }else{
            return null;
        }



    }


}
