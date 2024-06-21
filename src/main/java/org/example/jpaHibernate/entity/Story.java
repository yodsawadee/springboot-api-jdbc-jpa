package org.example.jpaHibernate.entity;

import javax.persistence.*;
import lombok.Data;

import java.sql.Timestamp;

@Data
@Entity
@Table(name = "STORIES")
public class Story {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name = "TITLE")
    private String title;


    @Column(name = "BODY")
    private String body;

    @Column(name = "AUTHOR")
    private String author;

    @Column(name = "IMG")
    private String img;

    @Column(name = "CREATED_AT", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public Story(Long id, String title, String body, String author, String img, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.author = author;
        this.img = img;
        this.createdAt = createdAt;
    }

    public Story() {
    }
}