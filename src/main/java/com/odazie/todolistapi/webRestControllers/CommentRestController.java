package com.odazie.todolistapi.webRestControllers;


import com.odazie.todolistapi.business.service.CommentService;
import com.odazie.todolistapi.business.service.TodoService;
import com.odazie.todolistapi.business.service.TokenBlacklistService;
import com.odazie.todolistapi.business.service.UserService;
import com.odazie.todolistapi.data.entity.Comment;
import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.entity.User;
import com.odazie.todolistapi.exception.ResourceNotFoundException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class CommentRestController {

    private final UserService userService;
    private final TodoService todoService;
    private final CommentService commentService;
    private final TokenBlacklistService blacklistService;


    public CommentRestController(UserService userService, TodoService todoService, CommentService commentService, TokenBlacklistService blacklistService) {
        this.userService = userService;
        this.todoService = todoService;
        this.commentService = commentService;
        this.blacklistService = blacklistService;
    }


    @GetMapping("/todos/{todoId}/comments")
    public ResponseEntity<Page<Comment>> getAllTodoComments(@PathVariable Long todoId, Pageable pageable, Authentication authentication, HttpServletRequest request) throws ResourceNotFoundException {
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);

        if(!todoService.getTodoRepository().existsByUserAndTodoId(currentUser,todoId)){
            throw new ResourceNotFoundException("Todo with + " + todoId + "not found");
        }
        return new ResponseEntity<>(commentService
                .getAllTodoComments(todo,pageable), HttpStatus.OK);
    }

    @PostMapping("/todos/{todoId}/comments")
    public ResponseEntity<Void> addComment(@RequestBody Comment comment, @PathVariable Long todoId, Authentication authentication , HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());

        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);

        commentService.addComment(todo,comment);

        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    @GetMapping("/todos/{todoId}/comments/{commentId}")
    public ResponseEntity<Comment> getCommentById(@PathVariable Long todoId, @PathVariable Long commentId, Authentication authentication , HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);

        Comment comment = commentService.getComment(todo,commentId);

        if (comment == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }

    @PutMapping("/todos/{todoId}/comments/{commentId}")
    public ResponseEntity<Comment> updateComment(@RequestBody Comment newCommentData, @PathVariable Long todoId, @PathVariable Long commentId, Authentication authentication , HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);

        Comment comment = commentService.getComment(todo,commentId);
        if(!commentService.getCommentRepository().existsByTodoAndCommentId(todo, commentId)){
            return new ResponseEntity<>(comment, HttpStatus.NOT_FOUND);
        }

        commentService.updateComment(comment,newCommentData);
        commentService.addComment(todo, comment);

        return new ResponseEntity<>(comment, HttpStatus.OK);
    }


    @DeleteMapping("/todos/{todoId}/comments/{commentId}")
    public ResponseEntity<Void> deleteComment(@PathVariable Long todoId, @PathVariable Long commentId, Authentication authentication , HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);

        Comment comment = commentService.getComment(todo,commentId);

        commentService.deleteComment(todo, comment);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }









}
