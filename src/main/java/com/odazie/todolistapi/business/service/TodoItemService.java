package com.odazie.todolistapi.business.service;

import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.entity.TodoItem;
import com.odazie.todolistapi.data.entity.User;
import com.odazie.todolistapi.data.repository.TodoItemRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class TodoItemService {

    private final TodoItemRepository todoItemRepository;


    public TodoItemService(TodoItemRepository todoItemRepository) {
        this.todoItemRepository = todoItemRepository;
    }

    public Page<TodoItem> getAllTodoItems(Todo todo, Pageable pageable){
        return getTodoItemRepository().findTodoItemsByTodo(todo, pageable);
    }

    public void addTodoItem(TodoItem todoItem, Todo todo){
        todoItem.setTodo(todo);
        todo.addItem(todoItem);
        getTodoItemRepository().save(todoItem);
    }

    public void updateTodoItem(TodoItem todoItem, TodoItem newTodoItemData){
        todoItem.setItemText(newTodoItemData.getItemText());
    }

    public void deleteTodoItem(TodoItem todoItem, Todo todo){
        todo.deleteItem(todoItem);
        getTodoItemRepository().delete(todoItem);
    }

    public TodoItem getItemByTodoAndItemId( Todo todo, Long itemId){
        return getTodoItemRepository().findByTodoAndItemId(todo, itemId);
    }

    public TodoItemRepository getTodoItemRepository() {
        return todoItemRepository;
    }
}
