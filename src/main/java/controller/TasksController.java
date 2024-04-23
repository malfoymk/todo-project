import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/tasks")
public class TasksController {
    
    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<Tasks> getTasks(@PathVariable Long id) {
        Tasks task = tasksService.getTasks(id);
        return ResponseEntity.ok().body(task);
    }
    
    @PostMapping
    public ResponseEntity<Tasks> createTask(@RequestBody Tasks tasks) {
        Tasks createdTask = tasksService.createTasks(tasks);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Tasks tasks) {
        tasksService.updateTasks(id, tasks);
        return ResponseEntity.noContent().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
        tasksService.deleteTask(id);
        return ResponseEntity.noContent().build();
    }

}
