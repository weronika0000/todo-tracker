package com.softwaremind.todotracker.boundary.dto;

import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import jakarta.validation.constraints.*;

import java.time.LocalDate;

public record AddAndModifyTaskRequestDto(

        @NotBlank(message = "Title cannot be empty")
        String title,
        @Size(max = 1800, message = "Details should not exceed 1800 characters")
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
