package com.softwaremind.todotracker.entity;

public enum TaskImportance {
    LOW,
    AVERAGE,
    HIGH;


    public static TaskImportance fromString(String importance) {
        for (TaskImportance taskImportance : TaskImportance.values()) {
            if (taskImportance.name().equalsIgnoreCase(importance)) {
                return taskImportance;
            }
        }
        throw new IllegalArgumentException("Invalid task importance: " + importance);
    }

}
