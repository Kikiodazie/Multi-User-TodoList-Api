package com.odazie.todolistapi.webRestControllers;

import com.odazie.todolistapi.business.service.TodoService;
import com.odazie.todolistapi.data.entity.Todo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

@RestController
public class TodoRest {

    private final TodoService todoservice;

    public TodoRest(TodoService todoservice) {
        this.todoservice = todoservice;
    }


    @GetMapping("/todos")
    public ResponseEntity<Page<Todo>> getAllTodos(Pageable pageable){
        return new ResponseEntity<Page<Todo>>(todoservice.getTodoRepository().findAll(pageable), HttpStatus.OK);
    }

    @PostMapping("/todos")
    public Todo createTodo(@RequestBody Todo todo){

        return todoservice.create(todo);

    }





}
