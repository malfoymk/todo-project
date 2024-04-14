package repository;


import model.Tarefas;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TarefasRepository extends JpaRepository<Tarefas, Long> {

    List<Tarefas> findByStatusTrue();

    List<Tarefas> findByStatusFalse();

    List<Tarefas> findByNomeContainingIgnoreCase(String keyword);
}