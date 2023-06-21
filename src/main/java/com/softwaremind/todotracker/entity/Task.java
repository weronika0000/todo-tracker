package com.softwaremind.todotracker.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;
import java.time.LocalDate;
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
    private String title;
    private String details;
    private TaskStatus status;
    private TaskImportance importance;
    private LocalDate deadline;
    private Instant createdAt;
    private Instant modifiedAt;

}
