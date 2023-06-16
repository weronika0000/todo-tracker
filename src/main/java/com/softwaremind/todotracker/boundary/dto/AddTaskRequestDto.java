package com.softwaremind.todotracker.boundary.dto;

import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import jakarta.validation.constraints.*;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;

public record AddTaskRequestDto(

        @NotBlank(message = "Title cannot be empty")
        String title,
        @NotBlank(message = "Title cannot be empty")
        String details,
        @NotNull
        TaskStatus status,
        @NotNull
        TaskImportance importance,

        @NotNull
        @FutureOrPresent
        LocalDate deadline

) {
}
