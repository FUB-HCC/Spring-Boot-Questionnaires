package de.fuberlin.hcc.questionnaires;

import de.fuberlin.hcc.questionnaires.QuestionnaireRepository;
import de.fuberlin.hcc.questionnaires.model.Question;
import de.fuberlin.hcc.questionnaires.model.Questionnaire;
import de.fuberlin.hcc.questionnaires.model.singlechoice.SingleChoiceQuestion;
import de.fuberlin.hcc.questionnaires.model.singlechoice.ratingblock.RatingBlock;
import de.fuberlin.hcc.questionnaires.model.text.TextQuestion;

import java.util.Arrays;

public class DataLoader {


 private QuestionnaireRepository questionnaireRepository;
   public DataLoader(QuestionnaireRepository questionnaireRepository){
       this.questionnaireRepository=questionnaireRepository;
   }
    public void createData(){
    createQuestions();


   }


    private  void createQuestions(){
        final Question q2 = new TextQuestion("Write your review to the movie");

        final Question q3 = new RatingBlock(
                "How would you rate the following items?",
                Arrays.asList("Very low", "Below average", "Average", "Above Average", "Very high"),
                Arrays.asList("The plot",
                        "Acting",
                        "Audio")
        );
        final Question q4 = new SingleChoiceQuestion("Would you recommend the movie to your friends?",
                Arrays.asList("Definitely not",
                        "Probably not ",
                        "Might or might not recommend",
                        "Probably recommend",
                        "Definitely recommend"));


        final Questionnaire result = new Questionnaire();
        result.add(q2);
        result.add(q3);
        result.add(q4);


        questionnaireRepository.save(result);
    }
}
