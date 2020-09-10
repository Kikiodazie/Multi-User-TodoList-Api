package com.odazie.todolistapi.data.entity;

import com.odazie.todolistapi.business.model.LabelColour;
import com.odazie.todolistapi.business.model.TodoStatus;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "todo")
public class Todo{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long todoId;

    @NotNull
    @Size(max = 100)
    @Column(name = "todo_title", unique = true)
    private String title;

    @Column(name = "description",columnDefinition = "TEXT")
    private String description;

    @Enumerated(EnumType.STRING)
    @Column(name = "label_colour")
    private LabelColour labelColour;

    @Enumerated(EnumType.STRING)
    @Column(name = "status")
    private TodoStatus todoStatus;

    @Temporal(TemporalType.TIMESTAMP)
    @CreatedDate
    @Column(name = "created_at")
    private Date createdAt = new Date();

    @Temporal(TemporalType.TIMESTAMP)
    @LastModifiedDate
    @Column(name = "updated_at")
    private Date updatedAt = new Date();

// Many to one between a user and todos
    @ManyToOne(fetch = FetchType.LAZY)
    private User user;


// One to Many Relationship between a t-odo and todo_items

    @OneToMany(
            mappedBy = "todo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<TodoItem> todoItems = new ArrayList<>();

    public void addItem(TodoItem todoItem){
        todoItems.add(todoItem);
        todoItem.setTodo(this);
    }

    public void deleteItem(TodoItem todoItem){
        todoItems.remove(todoItem);
        todoItem.setTodo(null);
    }





 // One to Many Relationship between a t-odo and comments
    @OneToMany(
            mappedBy = "todo",
            cascade = CascadeType.ALL,
            orphanRemoval = true
    )
    private final List<Comment> comments = new ArrayList<>();

    public void addComment(Comment comment){
        comments.add(comment);
        comment.setTodo(this);
    }

    public void deleteComment(Comment comment){
        comments.remove(comment);
        comment.setTodo(null);
    }









    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo )) return false;
        return todoId != null && todoId.equals(((Todo) o).getTodoId());
    }

    @Override
    public int hashCode() {
        return 31;
    }





    public Long getTodoId() {
        return todoId;
    }

    public void setTodoId(Long todoId) {
        this.todoId = todoId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LabelColour getLabelColour() {
        return labelColour;
    }

    public void setLabelColour(LabelColour labelColour) {
        this.labelColour = labelColour;
    }

    public TodoStatus getTodoStatus() {
        return todoStatus;
    }

    public void setTodoStatus(TodoStatus todoStatus) {
        this.todoStatus = todoStatus;
    }

    public User getUser() {
        return user;
    }

    public Date getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this.createdAt = createdAt;
    }

    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updated) {
        this.updatedAt = updated;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
