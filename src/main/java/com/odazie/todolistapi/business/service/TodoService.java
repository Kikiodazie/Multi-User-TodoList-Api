package com.odazie.todolistapi.business.service;

import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.entity.User;
import com.odazie.todolistapi.data.repository.TodoRepository;
import org.springframework.stereotype.Service;

@Service
public class TodoService {

    private final TodoRepository todoRepository;


    public TodoService(TodoRepository todoRepository) {
        this.todoRepository = todoRepository;
    }


    public void updateUserTodo(Todo todo, Todo newTodoData){
        todo.setTitle(newTodoData.getTitle());
        todo.setDescription(newTodoData.getDescription());
        todo.setDone(newTodoData.isDone());
        todo.setLabelColour(newTodoData.getLabelColour());
    }

    public void deleteTodo(Todo todo){
        getTodoRepository().delete(todo);
    }

    public Todo getTodoByUserAndId(User user, Long todoId){
        return getTodoRepository().findByUserAndTodoId(user, todoId);
    }

    public void addTodo(Todo todo){
        getTodoRepository().save(todo);
    }





    public TodoRepository getTodoRepository() {
        return todoRepository;
    }
}
