package com.odazie.todolistapi.data.repository;

import com.odazie.todolistapi.data.entity.Todo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TodoRepository extends JpaRepository<Todo, Long> {
}
