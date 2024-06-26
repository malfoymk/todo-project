package me.project.todo.model;

import javax.persistence.Column;
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
    
        @Column(name = "email", unique = true, nullable = false)
        private String email;



    public Account(Long id, String username, String password, String email) {
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
        if (password == null || password.isEmpty()) {
            throw new IllegalArgumentException("A senha não pode ser nula ou vazia");
        }
        if (!password.matches(".*[@!#&*%$].*")) {
            throw new IllegalArgumentException("A senha deve conter pelo menos um dos seguintes caracteres especiais: '@', '!', '#', '&', '*', '%', '$'");
        }
        this.password = password;
    }
    

    public String getPassword() {
        return password;
    }

     public String getEmail() {
         throw new UnsupportedOperationException("O email já está cadastrado");
     }

     public void setEmail(String email) {
         if (email == null || email.toString().isEmpty()) {
             throw new IllegalArgumentException("O email não pode ser nulo ou vazio");
         }
         if (!email.contains("@")) {
            throw new IllegalArgumentException("O email não é válido");
        }
         this.email = email;
     }
    
}
