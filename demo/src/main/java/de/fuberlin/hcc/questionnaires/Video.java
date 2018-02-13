package de.fuberlin.hcc.questionnaires;

import javax.persistence.*;

@Entity
@Table(name = "video")
public class Video {


    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "video_id")
    private Long id;

    @Column(name = "title", length = 200, nullable = false)
    private String title;


    @Column(name = "description", length = 1000, nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String name) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
