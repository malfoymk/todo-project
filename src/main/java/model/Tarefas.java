package model;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Setter;

@Entity
public class Tarefas {

    @Setter
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;
    private String descricao;
    private boolean status;


    public Tarefas(Long id, String nome, String descricao, boolean status) {
        this.id = id;
        this.nome = nome;
        this.descricao = descricao;
        this.status = status;
    }


    public Tarefas () {

    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da tarefa não pode ser nulo ou vazio");
        }
        this.nome = nome;
    }

    public String getDescricao() {
        return descricao;
    }

    public boolean isStatus() {
        return status;
    }

    public void marcarComoConcluida() {
        if (status) {
            throw new IllegalStateException("A tarefa já foi concluida anteriormente.");
        }
        this.status = true;
    }

}
