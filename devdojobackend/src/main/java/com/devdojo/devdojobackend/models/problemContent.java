package com.devdojo.devdojobackend.models;

import jakarta.persistence.Column;
// import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;

// import java.util.List;

@Entity
@Table(name = "problemcontent")
public class problemContent {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private Long pcid;
    private String title;
    @Column(length = 100000)
    private String content;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPcid() {
        return this.pcid;
    }

    public void setPcid(Long pcid) {
        this.pcid = pcid;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public problemContent() {

    }

    public problemContent(Long id, Long pcid, String title, String content) {
        this.id = id;
        this.pcid = pcid;
        this.title = title;
        this.content = content;
    }

}
