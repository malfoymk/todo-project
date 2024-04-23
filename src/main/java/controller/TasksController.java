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

    @GetMapping("/{id}")
    public Tasks getTasks(@PathVariable Long id) {
        return tasksService.getTasks(id);
    }
    
    @PostMapping
    public void createTasks (@RequestBody Tasks tasks) {
        tasksService.createTasks(tasks);
    }

    @PutMapping("/{id}")
    public void UpdateTasks(@PathVariable Long id, @RequestBody Tasks tasks) {
        tasksService.updateTasks(id, tasks);
    }


    @DeleteMapping("/{id}")
    public static deleteTask(@PathVariable Long id) {
        tasksService.deleteTask(id);
    }

}
