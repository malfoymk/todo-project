package me.project.todo.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import me.project.todo.model.Tasks;
import me.project.todo.repository.TasksRepository;
import me.project.todo.util.OperationTasks.OperationType;
import me.project.todo.util.OperationTasks;

import org.springframework.http.HttpStatus;

import java.util.Deque;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

@Service
public class TasksService {

    private static final Logger logger = LoggerFactory.getLogger(TasksService.class);

    private final TasksRepository tasksRepository;
    
    private final Deque<OperationTasks> operationHistory = new LinkedList<>();

    @Autowired
    public TasksService(TasksRepository tasksRepository) {
        this.tasksRepository = tasksRepository;
    }

    public Tasks getTask(Long id) {
        Optional<Tasks> taskOptional = tasksRepository.findById(id);
        if (taskOptional.isPresent()) {
            return taskOptional.get();
        } else {
            throw new TasksNotFoundException("Task not found: " + id);
        }
    }

    public Tasks createTask(Tasks newTask) {
        Tasks createdTask = tasksRepository.save(newTask);
        logger.info("Criando a tarefa com o ID: {}", createdTask.getId());
        operationHistory.push(new OperationTasks(OperationType.CREATE, createdTask));
        return createdTask;
    }

    public Tasks updateTask(Long id, Tasks newTask) {
        logger.info("Atualizando as tarefas com o ID: {}", id);
        Optional<Tasks> tasksExist = tasksRepository.findById(id);
        if (tasksExist.isPresent()) {
            Tasks task = tasksExist.get();
            task.setName(newTask.getName());
            task.setDescription(newTask.getDescription());
            task.setStatus(newTask.isStatus());
            operationHistory.push(new OperationTasks(OperationType.UPDATE, task));
            return tasksRepository.save(task);
        } else {
            throw new TasksNotFoundException("Task not found: " + id);
        }
    }

    public ResponseEntity<?> deleteTask(Long id) {
        logger.info("Deletando as tarefas com o ID: {}", id);
        Optional<Tasks> tasksOptional = tasksRepository.findById(id);
        if (tasksOptional.isPresent()) {
            tasksRepository.delete(tasksOptional.get());
            operationHistory.push(new OperationTasks(OperationType.DELETE, tasksOptional.get()));
            return ResponseEntity.ok().build();
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("The task with the ID " + id + " was not found.");
        }
    }

    public boolean undoLastOperation() {
        if(!operationHistory.isEmpty()) {
            OperationTasks lastOperation = operationHistory.pop();
            switch (lastOperation.getType()) {
                case CREATE:
                    tasksRepository.delete(lastOperation.getTask());
                    break;
                case UPDATE:
                    tasksRepository.save(lastOperation.getTask());
                    break;
                case DELETE:
                    tasksRepository.save(lastOperation.getTask());
                    break;
                default:
                    return false;
            }
            return true;
        }
        return false;   
    }

    public class TasksNotFoundException extends RuntimeException {
        public TasksNotFoundException(String message) {
            super(message);
        }
    }

    public List<Tasks> getAllTasks() {
        return tasksRepository.findAll();
    }
}
