package com.odazie.todolistapi.data.entity;

import com.odazie.todolistapi.business.model.AuditModel;

import javax.persistence.*;
import javax.validation.constraints.NotNull;


@Entity
@Table(name = "comment")
public class Comment extends AuditModel {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long commentId;

    @NotNull
    @Column(name = "comment_text", columnDefinition = "TEXT")
    private String commentText;

    @ManyToOne(fetch = FetchType.LAZY)
    private Todo todo;


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
