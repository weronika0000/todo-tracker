package com.softwaremind.todotracker.boundary.dto;

import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import lombok.Builder;

import java.time.Instant;
import java.time.LocalDate;
@Builder
public record TaskResponseDto(
        Long taskId,
        Long userId,
        String title,
        String details,
        TaskStatus status,
        TaskImportance importance,
        LocalDate deadline,
        Instant createdAt,
        Instant modifiedAt

) {
}
