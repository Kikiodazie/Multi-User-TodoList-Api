package com.odazie.todolistapi.business.service;

import com.odazie.todolistapi.data.entity.Comment;
import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.repository.CommentRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private final CommentRepository commentRepository;


    public CommentService(CommentRepository commentRepository) {
        this.commentRepository = commentRepository;
    }


    public Page<Comment> getAllTodoComments(Todo todo, Pageable pageable){
        return  getCommentRepository().findCommentsByTodo(todo, pageable);
    }

    public void addComment(Todo todo, Comment comment){
        comment.setTodo(todo);
        todo.addComment(comment);
        getCommentRepository().save(comment);
    }

    public Comment getComment(Todo todo, Long commentId){
        return getCommentRepository().getCommentByTodoAndCommentId(todo, commentId);
    }

    public void updateComment(Comment comment, Comment newCommentData){
        comment.setCommentText(newCommentData.getCommentText());
    }

    public void deleteComment( Todo todo, Comment comment){
        todo.deleteComment(comment);
        getCommentRepository().delete(comment);
    }

    public CommentRepository getCommentRepository() {
        return commentRepository;
    }
}
