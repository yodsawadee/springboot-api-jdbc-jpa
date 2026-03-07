package org.example.jpaHibernate.entity;

import jakarta.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Data;
import org.example.jpaHibernate.model.StoryRequest;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@Entity
@Table(name = "story")
public class Story {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    private String title;

    @Column
    private String body;

    @Column
    private String author;

    @Column
    private String img;

    @Column(name = "published_date")
    private LocalDateTime publishedDate;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false, updatable = false)
    private OffsetDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private OffsetDateTime updatedAt;

    public Story(String title, String body, String author, String img, LocalDateTime publishedDate) {
        this.title = title;
        this.body = body;
        this.author = author;
        this.img = img;
        this.publishedDate = publishedDate;
    }

    public Story(StoryRequest storyRequest) {
        this.title = storyRequest.getTitle();
        this.body = storyRequest.getBody();
        this.author = storyRequest.getAuthor();
        this.img = storyRequest.getImg();
        this.publishedDate = storyRequest.getPublishedDate();
    }

    public Story() {
    }
}