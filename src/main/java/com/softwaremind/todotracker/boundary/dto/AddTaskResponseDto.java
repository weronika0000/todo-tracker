package com.softwaremind.todotracker.boundary.dto;

import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
@Builder
public record AddTaskResponseDto(

        Long taskId,
        String title,
        String details,
        TaskStatus status,
        TaskImportance importance,
        LocalDate deadline,
        Instant createdAt

)



 {
}
