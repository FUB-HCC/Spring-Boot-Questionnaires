package de.fuberlin.hcc.questionnaires;

import de.fuberlin.hcc.questionnaires.model.QuestionnaireWithAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;

@Controller
public class VideosController {


    private EditService editService;
    private QuestionnaireSummaryService summaryService;
    private ViewService viewService;
    private VideoRepository videoRepository;

    @Autowired
    public VideosController(EditService editService,
                            QuestionnaireSummaryService summaryService,
                            ViewService viewService,
                            VideoRepository videoRepository){
        this.editService = editService;
        this.summaryService = summaryService;
        this.viewService = viewService;
        this.videoRepository = videoRepository;

    }

    @RequestMapping("/")
    public String showVideos(Model model){
        List<Video> videos = (List<Video> )videoRepository.findAll();

        model.addAttribute("videos", videos);
        return  "main";
    }


    @RequestMapping("/view?contextKey={contextKey}&questionnaireId={questionnaireId}&userId={userId}")
    public String showQuestionnaire(@PathVariable long contextKey,
                                    @PathVariable long questionnaireId,
                                    @PathVariable long userId,
                                    Model model){

        QuestionnaireWithAnswers questionnaireWithAnswers=
                viewService.getQuestionnaireWithAnswers(questionnaireId,contextKey,userId);
        model.addAttribute("questionnaire", questionnaireWithAnswers);
        return "questionnaire";
    }

}
