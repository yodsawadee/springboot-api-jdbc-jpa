package org.example.jdbc.model;

import lombok.Data;

import javax.persistence.*;
import java.sql.Timestamp;

@Data
@Entity
@Table(name = "STORIES")
public class StoryEntity {

    @Id
    @Column(name = "ID")
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;


    @Column(name = "TITLE")
    private String title;


    @Column(name = "BODY")
    private String body;

    @Column(name = "CREATED_AT", nullable = false, updatable = false, insertable = false, columnDefinition = "TIMESTAMP DEFAULT CURRENT_TIMESTAMP")
    private Timestamp createdAt;

    public StoryEntity(Long id, String title, String body, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public StoryEntity() {
    }
}