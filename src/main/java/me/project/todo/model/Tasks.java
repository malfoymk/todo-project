package me.project.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Setter;

@Entity
public class Tasks {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String description;
    private boolean status;


    public Tasks(Long id, String name, String description, boolean status) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.status = status;
    }


    public Tasks () {

    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa não pode ser nulo ou vazio");
        }
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public void setStatus(boolean status) {
        this.status = status;
    }
    public String getDescription() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public void markForComplet() {
        if (status) {
            throw new IllegalStateException("A tarefa já foi concluída anteriormente.");
        }
        this.status = true;
    }

}
