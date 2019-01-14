package de.fuberlin.hcc.questionnaires.demo;


import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VideoRepository extends CrudRepository<Video, Long> {




    @Query("Select i FROM  Video i, AnswerSession a WHERE i.id=a.contextKey and " +
            "a.questionnaireId=:qId and a.userId=:uId")
    List<Video> findRatedVideos(@Param("qId") long qId, @Param("uId") long uId);


    @Query("Select i FROM  Video i where  i.id  not in :videoIds")
    List<Video> findNotRatedVideos(@Param("videoIds") List<Long> videosIds);
}
