package me.project.todo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import me.project.todo.model.Tasks;

import java.util.List;

@Repository
public interface TasksRepository extends JpaRepository<Tasks, Long> {

    List<Tasks> findByStatusTrue();

    List<Tasks> findByStatusFalse();

    List<Tasks> findByNameContainingIgnoreCase(String keyword);
}
