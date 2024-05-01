    package me.project.todo.controller;

    import org.springframework.beans.factory.annotation.Autowired;
    import org.springframework.http.HttpStatus;
    import org.springframework.http.ResponseEntity;
    import org.springframework.web.bind.annotation.*;

    import io.swagger.annotations.Api;
    import me.project.todo.service.TasksService;
    import me.project.todo.model.Tasks;

    @Api(value="/api/tasks", tags = "Tasks")
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
            Tasks task = tasksService.getTask(id);
            return ResponseEntity.ok().body(task);
        }
        
        @PostMapping
        public ResponseEntity<Tasks> createTask(@RequestBody Tasks tasks) {
            Tasks createdTask = tasksService.createTask(tasks);
            return ResponseEntity.status(HttpStatus.CREATED).body(createdTask);
        }

        @PutMapping("/{id}")
        public ResponseEntity<Void> updateTask(@PathVariable Long id, @RequestBody Tasks tasks) {
            tasksService.updateTask(id, tasks);
            return ResponseEntity.noContent().build();
        }

        @DeleteMapping("/{id}")
        public ResponseEntity<Void> deleteTask(@PathVariable Long id) {
            tasksService.deleteTask(id);
            return ResponseEntity.noContent().build();
        }

        @GetMapping("/theme")
        public String getTheme(@RequestHeader(value = "Mode Preference", defaultValue = "Light mode") String themePrefence) {
            if("Dark mode".equalsIgnoreCase(themePrefence)) {
                return "Dark mode";
            } else {
                return "Light mode";
            }   
        }

    }
