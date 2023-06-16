package com.softwaremind.todotracker.boundary.dto;

import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;
import lombok.Builder;

import java.time.LocalDate;

@Builder
public record ModifyTaskResponseDto(

        Long taskId,
        Long userId,
        String title,
        String details,
        TaskStatus status,
        TaskImportance importance,
        LocalDate deadline)



 {
}
