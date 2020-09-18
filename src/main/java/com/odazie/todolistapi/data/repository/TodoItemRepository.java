package com.odazie.todolistapi.data.repository;

import com.odazie.todolistapi.data.entity.Todo;
import com.odazie.todolistapi.data.entity.TodoItem;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem , Long> {

    Page<TodoItem> findTodoItemsByTodo(Todo todo, Pageable pageable);

    TodoItem findByTodoAndItemId(Todo todo, Long itemId);

    boolean existsByTodoAndItemId(Todo todo, Long itemId);
}
