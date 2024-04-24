package repository;


import model.Tasks;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByStatusTrue();

    List<Tasks> findByStatusFalse();

    List<Tasks> findByNomeContainingIgnoreCase(String keyword);
}