package com.softwaremind.todotracker.boundary.dto;

import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDate;

public record ModifyTaskRequestDto(

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
