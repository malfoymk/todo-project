package model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
            throw new IllegalArgumentException("O name da tarefa não pode ser nulo ou vazio");
        }
        this.name = name;
    }

    public String getDescricao() {
        return description;
    }

    public boolean isStatus() {
        return status;
    }

    public void markForComplet() {
        if (status) {
            throw new IllegalStateException("A tarefa já foi concluida anteriormente.");
        }
        this.status = true;
    }

}
