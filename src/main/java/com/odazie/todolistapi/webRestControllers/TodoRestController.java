package com.odazie.todolistapi.webRestControllers;

import com.odazie.todolistapi.business.service.TodoService;
import com.odazie.todolistapi.business.service.TokenBlacklistService;
import com.odazie.todolistapi.business.service.UserService;
import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.entity.User;
import com.odazie.todolistapi.exception.ResourceNotFoundException;
import com.odazie.todolistapi.security.JWTAuthorizationFilter;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@RestController
public class TodoRestController {

    private final TodoService todoservice;
    private final UserService userService;
    private TokenBlacklistService blacklistService;

    public TodoRestController(TodoService todoservice, UserService userService, TokenBlacklistService blacklistService) {
        this.todoservice = todoservice;
        this.userService = userService;
        this.blacklistService = blacklistService;
    }


    @GetMapping("/todos")
    public ResponseEntity<Page<Todo>> getAllTodosByUser(Authentication authentication, Pageable pageable, HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User currentUser = userService.findUserByEmail(authentication.getName());
        return new ResponseEntity<Page<Todo>>(todoservice.getTodoRepository().findByUser(currentUser, pageable ), HttpStatus.OK);
    }

    @PostMapping("/todos")
    public ResponseEntity<Void> createTodo(@RequestBody Todo todo, Authentication authentication, HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        User currentUser = userService.findUserByEmail(authentication.getName());
        todoservice.addTodo(todo, currentUser);
        return new ResponseEntity<Void>(HttpStatus.CREATED);
    }

    @GetMapping("/todos/{todoId}")
    public ResponseEntity<Todo> getUserTodoById(@PathVariable("todoId") Long todoId, Authentication authentication , HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());

        Todo todo = todoservice.getTodoByUserAndId(currentUser, todoId);
        if(todo == null){
            return new ResponseEntity<Todo>((Todo) null, HttpStatus.NOT_FOUND);
        }

        return new ResponseEntity<Todo>(todo, HttpStatus.OK);
    }

    @PutMapping("/todos/{todoId}")
    public ResponseEntity<Todo> updateUserTodo(@PathVariable Long todoId, @RequestBody Todo newTodoData, Authentication authentication, HttpServletRequest request) throws ResourceNotFoundException {
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoservice.getTodoByUserAndId(currentUser, todoId);

        if(!todoservice.getTodoRepository().existsByUserAndTodoId(currentUser,todoId)){
            throw new ResourceNotFoundException("Todo with + " + todoId + "not found");
        }

        todoservice.updateUserTodo(todo, newTodoData);
        todoservice.addTodo(todo,currentUser);
        return new ResponseEntity<Todo>(todo,HttpStatus.OK);
    }


    @DeleteMapping("todos/{todoId}")
    public  ResponseEntity<Void> deleteUserTodo(@PathVariable Long todoId, Authentication authentication, HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoservice.getTodoByUserAndId(currentUser, todoId);

        todoservice.deleteTodo(todo,currentUser);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }














}
