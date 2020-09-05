package com.odazie.todolistapi.data.repository;

import com.odazie.todolistapi.data.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
