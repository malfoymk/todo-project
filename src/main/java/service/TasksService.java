package service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import model.Tasks;
import repository.TasksRepository;
import java.util.Optional;

@Service
public class TasksService {

    private final TasksRepository tasksRepository;

    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public void excluirTarefa(Long id) {
        Optional<Tasks> tasksOptional = tasksRepository.findById(id);
        if (tasksOptional.isPresent()) {
            tasksRepository.delete(tasksOptional.get());
        } else {
            throw new RuntimeException("Tarefa não encontrada: " + id);
        }
    }
}
