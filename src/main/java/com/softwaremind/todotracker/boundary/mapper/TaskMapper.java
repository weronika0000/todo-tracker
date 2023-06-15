package com.softwaremind.todotracker.boundary.mapper;

import com.softwaremind.todotracker.boundary.dto.AddTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.entity.Task;

public class TaskMapper {

    public static Task mapTaskRequestDtotoTask(AddTaskRequestDto taskDto){
        return Task.builder()
                .userId(taskDto.userId())
                .title(taskDto.title())
                .details(taskDto.details())
                .status(taskDto.status())
                .importance(taskDto.importance())
                .deadline(taskDto.deadline())
                .build();

    }

    public static AddTaskResponseDto mapTasktoTaskResponseDto(Task task){
        return AddTaskResponseDto.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .details(task.getDetails())
                .status(task.getStatus())
                .importance(task.getImportance())
                .deadline(task.getDeadline())
                .build();

    }


}
