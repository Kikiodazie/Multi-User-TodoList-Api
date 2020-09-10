package com.odazie.todolistapi.data.entity;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Date;


@Entity
@Table(name = "comment")
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotNull
    @Column(name = "comment_text", columnDefinition = "TEXT")
    private String commentText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;


    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt = new Date();



    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo )) return false;
        return commentId != null && commentId.equals(((Comment) o).getCommentId());
    }

    @Override
    public int hashCode() {
        return 31;
    }


    public Long getCommentId() {
        return commentId;
    }

    public void setCommentId(Long commentId) {
        this.commentId = commentId;
    }

    public String getCommentText() {
        return commentText;
    }

    public void setCommentText(String commentText) {
        this.commentText = commentText;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }
}
