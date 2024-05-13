package me.project.todo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Setter;

@Entity
public class Account {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String username;
    private String password;
    private Object email;



    public Account(Long id, String username, String password, Object email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.email = email;
    }
    
    public Account() {
        
    }

    public Long getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        if (username == null || username.isEmpty()) {
            throw new IllegalArgumentException("O nome do usuário não pode ser nulo ou vazio");
        }
        this.username = username;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }

    public Object getEmail() {
        throw new UnsupportedOperationException("O email já está cadastrado");
    }

    public void setEmail(Object email) {
        if (email == null || email.toString().isEmpty()) {
            throw new IllegalArgumentException("O email não pode ser nulo ou vazio");
        }
        this.email = email;
    }
    
}
