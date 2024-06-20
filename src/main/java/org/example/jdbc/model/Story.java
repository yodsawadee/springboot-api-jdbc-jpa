package org.example.jdbc.model;

import lombok.Data;

import java.sql.Timestamp;

@Data
public class Story {

    private Long id;


    private String title;


    private String body;


    private Timestamp createdAt;

    public Story(Long id, String title, String body, Timestamp createdAt) {
        this.id = id;
        this.title = title;
        this.body = body;
        this.createdAt = createdAt;
    }

    public Story() {
    }
}