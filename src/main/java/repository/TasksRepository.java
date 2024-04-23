package repository;


import model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tarefas> findByStatusTrue();

    List<Tarefas> findByStatusFalse();

    List<Tarefas> findByNomeContainingIgnoreCase(String keyword);
}