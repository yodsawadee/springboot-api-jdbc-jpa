package org.example.jpaHibernate.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jpaHibernate.entity.Story;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class StoryResponse {
    private Long id;
    private String title;
    private String body;
    private String author;
    private String img;

    @JsonFormat(pattern = "yyyy-MM-dd'T'HH:mm:ss")
    private LocalDateTime publishedDate;

    private OffsetDateTime createdAt;

    private OffsetDateTime updatedAt;

    public StoryResponse(Story story) {
        this.id = story.getId();
        this.title = story.getTitle();
        this.body = story.getBody();
        this.author = story.getAuthor();
        this.img = story.getImg();
        this.publishedDate = story.getPublishedDate();
        this.createdAt = story.getCreatedAt();
        this.updatedAt = story.getUpdatedAt();
    }
}