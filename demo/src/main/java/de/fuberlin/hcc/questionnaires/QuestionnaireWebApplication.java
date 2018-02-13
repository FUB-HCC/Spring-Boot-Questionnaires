package de.fuberlin.hcc.questionnaires;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;


@SpringBootApplication
public class QuestionnaireWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireWebApplication.class, args);
    }


    @Bean
    @Autowired
    public CommandLineRunner loadData(QuestionnaireRepository questionnaireRepository){
        return (args) -> {
                DataLoader dataLoader = new DataLoader(questionnaireRepository);
                dataLoader.createData();
        };
    }




}
