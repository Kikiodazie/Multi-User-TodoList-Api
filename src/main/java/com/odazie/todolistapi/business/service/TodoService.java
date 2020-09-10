package com.odazie.todolistapi.business.service;

import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }

//    public List<Todo> getUserTodos(){
//
//    }

    public Todo create(Todo todo){
        getTodoRepository().save(todo);
        return todo;
    }

    public TodoRepository getTodoRepository() {
        return todoRepository;
    }
}
