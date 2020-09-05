package com.odazie.todolistapi.data.entity;


import com.odazie.todolistapi.business.model.AuditModel;


import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotNull;

import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "app_user")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Size(max = 65)
    @Column(name = "first_name")
    private String firstName;


    @Size(max = 65)
    @Column(name = "last_name")
    private String lastName;


    @NotNull
    @Email
    @Size(max = 100)
    @Column(name = "email", unique = true)
    private String email;

    @OneToMany(
            mappedBy = "user",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Todo> todoList = new ArrayList<>();

    public void createTodo(Todo todo){
        todoList.add(todo);
        todo.setUser(this);
    }

    public void deleteTodo(Todo todo){
        todoList.remove(todo);
        todo.setUser(null);
    }



    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
