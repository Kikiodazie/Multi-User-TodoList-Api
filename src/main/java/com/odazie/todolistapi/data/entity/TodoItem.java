package com.odazie.todolistapi.data.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

@Entity
@Table(name = "todo_item")
public class TodoItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long itemId;


    @Column(name = "item_text", columnDefinition = "TEXT")
    private String itemText;



    @ManyToOne(fetch = FetchType.LAZY)
    @JsonIgnore
    private Todo todo;




    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Todo )) return false;
        return itemId != null && itemId.equals(((TodoItem) o).getItemId());
    }

    @Override
    public int hashCode() {
        return 31;
    }

    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    public String getItemText() {
        return itemText;
    }

    public void setItemText(String itemText) {
        this.itemText = itemText;
    }

    public Todo getTodo() {
        return todo;
    }

    public void setTodo(Todo todo) {
        this.todo = todo;
    }



}
