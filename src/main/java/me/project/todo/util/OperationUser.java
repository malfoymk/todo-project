package me.project.todo.util;

import me.project.todo.model.Account;

public class OperationUser {
    
    private final OperationType type;
    private final Account user;

    public OperationUser(OperationType type, Account user) {
        this.type = type;
        this.user = user;
    }

    public OperationType getType() {
        return type;
    }

    public Account getUser() {
        return user;
    }

    public enum OperationType {
        CREATE, UPDATE, DELETE
    }
    
}
