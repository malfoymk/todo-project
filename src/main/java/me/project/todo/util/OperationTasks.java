package me.project.todo.util;

import me.project.todo.model.Tasks;

public class OperationTasks {

    private final OperationType type;
    private final Tasks task;

    public OperationTasks(OperationType type, Tasks task) {
        this.type = type;
        this.task = task;
    }

    public OperationType getType() {
        return type;
    }

    public Tasks getTask() {
        return task;
    }

    public enum OperationType {
        CREATE, UPDATE, DELETE
    }
}
