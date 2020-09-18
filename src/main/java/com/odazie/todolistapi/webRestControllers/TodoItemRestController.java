package com.odazie.todolistapi.webRestControllers;

import com.odazie.todolistapi.business.service.TodoItemService;
import com.odazie.todolistapi.business.service.TodoService;
import com.odazie.todolistapi.business.service.TokenBlacklistService;
import com.odazie.todolistapi.business.service.UserService;
import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.entity.TodoItem;
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
public class TodoItemRestController {

    private final TodoItemService todoItemService;
    private final UserService userService;
    private final TodoService todoService;
    private final TokenBlacklistService blacklistService;



    public TodoItemRestController(TodoItemService todoItemService, UserService userService, TodoService todoService, TokenBlacklistService blacklistService) {
        this.todoItemService = todoItemService;
        this.userService = userService;
        this.todoService = todoService;
        this.blacklistService = blacklistService;
    }

    @GetMapping("todos/{todoId}/items")
    public ResponseEntity<Page<TodoItem>> getAllTodoItems(@PathVariable Long todoId, Pageable pageable, Authentication authentication, HttpServletRequest request) throws ResourceNotFoundException {
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);

        if(!todoService.getTodoRepository().existsByUserAndTodoId(currentUser,todoId)){
            throw new ResourceNotFoundException("Todo with + " + todoId + "not found");
        }
        return new ResponseEntity<>(todoItemService
                .getAllTodoItems(todo, pageable), HttpStatus.OK);
    }


    @PostMapping("todos/{todoId}/items")
    public ResponseEntity<Void> createTodoItem(@RequestBody TodoItem todoItem, Authentication authentication, @PathVariable Long todoId, HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());

        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);
        todoItemService.addTodoItem(todoItem, todo);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }


    @GetMapping("todos/{todoId}/items/{itemId}")
    public ResponseEntity<TodoItem> getTodoItemById(@PathVariable Long todoId, @PathVariable Long itemId, Authentication authentication, HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);

        TodoItem item = todoItemService.getItemByTodoAndItemId(todo, itemId);
        if (item == null){
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(item, HttpStatus.OK);
    }


    @PutMapping("todos/{todoId}/items/{itemId}")
    public ResponseEntity<TodoItem> updateTodoItem(@RequestBody TodoItem newTodoId, @PathVariable Long todoId, @PathVariable Long itemId, Authentication authentication, HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);
        TodoItem item = todoItemService.getItemByTodoAndItemId(todo, itemId);

        if (!todoItemService.getTodoItemRepository().existsByTodoAndItemId(todo,itemId)){
            return new ResponseEntity<>(item, HttpStatus.NOT_FOUND);
        }

        todoItemService.updateTodoItem(item, newTodoId);
        todoItemService.addTodoItem(item,todo);

        return new ResponseEntity<>(item, HttpStatus.OK);
    }

    @DeleteMapping("todos/{todoId}/items/{itemId}")
    public ResponseEntity<Void> deleteTodoItem(@PathVariable Long todoId, @PathVariable Long itemId, Authentication authentication, HttpServletRequest request){
        if(blacklistService.blacklistCheck(request) != null){
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }
        User currentUser = userService.findUserByEmail(authentication.getName());
        Todo todo = todoService.getTodoByUserAndId(currentUser, todoId);
        TodoItem item = todoItemService.getItemByTodoAndItemId(todo, itemId);

        todoItemService.deleteTodoItem(item,todo);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }










}
