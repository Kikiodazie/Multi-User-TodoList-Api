package com.odazie.todolistapi.data.repository;

import com.odazie.todolistapi.data.entity.Comment;
import com.odazie.todolistapi.data.entity.Todo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    Page<Comment>  findCommentsByTodo(Todo todo, Pageable pageable);

    Comment getCommentByTodoAndCommentId(Todo todo, Long commentId);

    boolean existsByTodoAndCommentId(Todo todo, Long commentId);


}
