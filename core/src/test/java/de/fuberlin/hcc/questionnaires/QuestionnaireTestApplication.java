package de.fuberlin.hcc.questionnaires;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = "de.fuberlin.hcc.questionnaires")
@EntityScan( basePackages = {"de.fuberlin.hcc.questionnaires"} )
public class QuestionnaireTestApplication {
}
