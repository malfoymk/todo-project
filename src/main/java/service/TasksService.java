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

    public Tasks createTask(Tasks newTask) {
        return tasksRepository.save(newTask);
    }

    public Tasks updateTask(Long id, Tasks newTask) {
        Optional<Tasks> tasksExist = tasksRepository.findById(id);
        if(tasksExist.isPresent()) {
            Tasks task = tasksExist.get();
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setStatus(newTask.isStatus());
            return tasksRepository.save(task);
        }else {
            throw new TasksNotFoundException("Task not found: " + id);
        }
    }
    
    public ResponseEntity<?> deleteTask(Long id) {
        Optional<Tasks> tasksOptional = tasksRepository.findById(id);
        if (tasksOptional.isPresent()) {
            tasksRepository.delete(tasksOptional.get());
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
            .body("The task with the ID " + id + " was not found.");
        }
    }

}
