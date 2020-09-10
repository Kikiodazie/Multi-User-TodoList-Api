package com.odazie.todolistapi.data.repository;

import com.odazie.todolistapi.data.entity.TodoItem;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TodoItemRepository extends JpaRepository<TodoItem , Long> {
}
