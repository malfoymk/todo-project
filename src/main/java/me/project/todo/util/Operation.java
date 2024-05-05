package me.project.todo.util;

import me.project.todo.model.Tasks;

public class Operation {

    private final OperationType type;
    private final Tasks task;

    public Operation(OperationType type, Tasks task) {
        this.type = type;
        this.task = task;
    }

    public OperationType getType() {
        return type;
    }

    public Tasks getTask() {
        return task;
    }

    // Enumeração para representar o tipo de operação
    public enum OperationType {
        CREATE, UPDATE, DELETE
    }
}
