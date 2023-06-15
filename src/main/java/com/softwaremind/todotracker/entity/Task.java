package com.softwaremind.todotracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDateTime;

@Entity(name="Tasks")

@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)

    private Long taskId;
    private Long userId;
    private String taskTitle;
    private String taskDetails;
    private TaskStatus taskStatus;
    private TaskImportance taskImportance;
    private Instant taskCreatedAt;
    private LocalDateTime taskLastModifiedAt;
    private LocalDateTime taskDeadline;


}
