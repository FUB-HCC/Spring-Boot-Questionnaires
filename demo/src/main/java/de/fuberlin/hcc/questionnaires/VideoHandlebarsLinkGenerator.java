package de.fuberlin.hcc.questionnaires;


import pl.allegro.tech.boot.autoconfigure.handlebars.HandlebarsHelper;

@HandlebarsHelper
public class VideoHandlebarsLinkGenerator {


    public static String resolveVideoActionLink(String action,long  questionnaireId,  long userId, long videoId) {

        return action+"?qId="+questionnaireId+"&uId="+userId+"&videoId="+videoId;
       // return action+"/"+videoId;
    }
}
