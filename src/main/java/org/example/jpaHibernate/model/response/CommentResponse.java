package org.example.jpaHibernate.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.jpaHibernate.entity.Comment;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CommentResponse {
    private Long id;
    private String name;
    private String email;
    private String body;

    private PostResponse post;

    public CommentResponse(Comment comment) {
        this.id = comment.getId();
        this.name = comment.getName();
        this.email = comment.getEmail();
        this.body = comment.getBody();

        if (comment.getPost() != null) {
            this.post = new PostResponse(comment.getPost());
        }
    }
}
