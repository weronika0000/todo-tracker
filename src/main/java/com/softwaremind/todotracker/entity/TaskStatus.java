package com.softwaremind.todotracker.entity;

public enum TaskStatus {
    TODO,
    DOING,
   DONE;
    public static TaskStatus fromString(String status) {
        for (TaskStatus taskStatus : TaskStatus.values()) {
            if (taskStatus.name().equalsIgnoreCase(status)) {
                return taskStatus;
            }
        }
        throw new IllegalArgumentException("Invalid task status: " + status);
    }
}
