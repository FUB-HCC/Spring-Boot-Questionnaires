package de.fuberlin.hcc.questionnaires;

import javax.persistence.*;

@Entity
@Table(name = "video")
public class Video {



    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "video_id")
    private Long id;

    @Column(name = "title", length = 200, nullable = false)
    private String name;


    @Column(name = "description", length = 500, nullable = false)
    private String description;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

}
