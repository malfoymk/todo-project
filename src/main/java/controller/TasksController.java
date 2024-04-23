import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;



@RequestController
@RequestMapping("/api/tasks")
public class TasksController {
    
    private final TasksService tasksService;

    @Autowired
    public TasksController(TasksService tasksService) {
        this.tasksService = tasksService;
    }

    @DeleteMapping("/{id}")
    public static deleteTask(@PathVariable Long id) {
        tasksService.deleteTask(id);
    }

}
