package com.softwaremind.todotracker.boundary.mapper;

import com.softwaremind.todotracker.boundary.dto.AddAndModifyTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.boundary.dto.TaskResponseDto;
import com.softwaremind.todotracker.entity.Task;

import java.util.List;
import java.util.stream.Collectors;

public class TaskMapper {

    public static Task mapAddAndModifyTaskRequestDtotoTask(AddAndModifyTaskRequestDto taskDto){
        return Task.builder()
                .title(taskDto.title())
                .details(taskDto.details())
                .status(taskDto.status())
                .importance(taskDto.importance())
                .deadline(taskDto.deadline())
                .build();

    }

    public static AddTaskResponseDto mapTasktoAddTaskResponseDto(Task task){
        return AddTaskResponseDto.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .details(task.getDetails())
                .status(task.getStatus())
                .importance(task.getImportance())
                .deadline(task.getDeadline())
                .createdAt(task.getCreatedAt())
                .build();

    }

    public static TaskResponseDto mapTasktoTaskResponseDto(Task task){
        return TaskResponseDto.builder()
                .taskId(task.getTaskId())
                .title(task.getTitle())
                .details(task.getDetails())
                .status(task.getStatus())
                .importance(task.getImportance())
                .deadline(task.getDeadline())
                .createdAt(task.getCreatedAt())
                .modifiedAt(task.getModifiedAt())
                .build();

    }



    public static List<TaskResponseDto> mapTaskListToTaskResponseDtoList(List<Task> listOfTasks) {
        return listOfTasks.stream()
                .map(TaskMapper::mapTasktoTaskResponseDto)
                .collect(Collectors.toList());
    }



}
