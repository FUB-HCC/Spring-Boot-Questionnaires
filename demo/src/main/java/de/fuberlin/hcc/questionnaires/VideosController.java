package de.fuberlin.hcc.questionnaires;


import de.fuberlin.hcc.questionnaires.model.QuestionnaireSummary;
import de.fuberlin.hcc.questionnaires.model.QuestionnaireWithAnswers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Controller
public class VideosController {


    private EditService editService;
    private QuestionnaireSummaryService summaryService;
    private QuestionnaireWithAnswersService  questionnaireWithAnswersService;
    private ViewService viewService;
    private VideoRepository videoRepository;

    @Autowired
    public VideosController(QuestionnaireWithAnswersService questionnaireService,
                            EditService editService,
                            QuestionnaireSummaryService summaryService,
                            ViewService viewService,
                            VideoRepository videoRepository){
        this.questionnaireWithAnswersService = questionnaireService;
        this.editService = editService;
        this.summaryService = summaryService;
        this.viewService = viewService;
        this.videoRepository = videoRepository;

    }

    @GetMapping("/")
    public String showVideos(@RequestParam(value = "uId") Long userId,
                             @RequestParam(value="qId") Long questionnaireId, Model model ){

        List<Video> ratedVideos = (List<Video> )videoRepository.findRatedVideos(questionnaireId,userId);
        model.addAttribute("ratedVideos", ratedVideos);
        List<Video> allVideos = (List<Video>) videoRepository.findAll();
        model.addAttribute("allVideos", allVideos);

      if(ratedVideos == null){
          model.addAttribute("notRatedVideos", videoRepository.findAll());
      }else {
          List<Long> ratedsVideosIds = ratedVideos.stream().map(v -> v.getId()).collect(Collectors.toList());
          model.addAttribute("notRatedVideos", videoRepository.findNotRatedVideos(ratedsVideosIds));
      }
        model.addAttribute("uId",userId);
        model.addAttribute("qId", questionnaireId);

        return  "main";
    }


    @RequestMapping("/rate")
    public String rateVideo(@RequestParam Long qId,
                            @RequestParam Long uId,
                            @RequestParam long videoId,
                             Model model){
        QuestionnaireWithAnswers questionnaireWithAnswers=
                viewService.getQuestionnaireWithAnswers(qId,videoId,uId);
        model.addAttribute("questionnaire", questionnaireWithAnswers);
        model.addAttribute("qId", qId);
        model.addAttribute("uId", uId);
        model.addAttribute("videoId", videoId);
        return "rate-one";

    }

    @RequestMapping("/view")
    public String viewVideo(@RequestParam Long qId,
                            @RequestParam Long uId,
                            @RequestParam long videoId,
                            Model model){
        QuestionnaireWithAnswers questionnaireWithAnswers=
                viewService.getQuestionnaireWithAnswers(qId,videoId,uId);
        model.addAttribute("questionnaire", questionnaireWithAnswers);
        model.addAttribute("qId", qId);
        model.addAttribute("uId", uId);
        model.addAttribute("videoId", videoId);
        return "view-one";

    }


    @RequestMapping("/summary")
    public String viewVideoSummary(@RequestParam Long qId,
                            @RequestParam long videoId,
                            Model model){


        QuestionnaireSummary questionnaireSummary = summaryService.getSummaryFor(qId,videoId);

        model.addAttribute("qId", qId);
        model.addAttribute("qId", qId);
        model.addAttribute("videoId", videoId);
        model.addAttribute("videoRatings", questionnaireSummary);
        return "summary-one";

    }


    @PostMapping("/rate")
    @ResponseBody
    public ResponseEntity rateIdea(HttpServletRequest request) {
        try{


            final long qId = Long.parseLong(request.getParameter("qId"));
            final long videoId =  Long.parseLong(request.getParameter("videoId"));
            final long uId =  Long.parseLong(request.getParameter("uId"));
            editService.update(qId, uId, videoId,request);
            final HttpHeaders headers = new HttpHeaders();
            headers.add("Location", "/?qId=" +qId+"&uId="+uId);
            return new ResponseEntity<String>(headers, HttpStatus.FOUND);
        } catch (Exception e) {

            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        }
    }




}
