package de.fuberlin.hcc.questionnaires.demo;

import de.fuberlin.hcc.questionnaires.QuestionnaireRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;


@SpringBootApplication
@ComponentScan(basePackages = {"de.fuberlin.hcc.questionnaires"})
@EntityScan(basePackages = {"de.fuberlin.hcc.questionnaires"})
@EnableJpaRepositories("de.fuberlin.hcc.questionnaires")
public class QuestionnaireWebApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuestionnaireWebApplication.class, args);
    }


    @Bean
    @Autowired
    public CommandLineRunner loadData(QuestionnaireRepository questionnaireRepository) {
        return (args) -> {
            DataLoader dataLoader = new DataLoader(questionnaireRepository);
            dataLoader.createData();
        };
    }
}
