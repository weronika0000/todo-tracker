package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.*;
import com.softwaremind.todotracker.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    AddTaskResponseDto addTask(AddTaskRequestDto taskDto);

    TaskResponseDto getById(Long taskId);

    List<TaskResponseDto> getAllTasks();

    ModifyTaskResponseDto modifyTask(Long taskId, ModifyTaskRequestDto taskDto);

    void deleteTask(Long taskId);


    List<TaskResponseDto> getTasksByStatusSortedByDeadline(TaskStatus status);

    List<TaskResponseDto> getTasksByStatusSortedByImportance(TaskStatus status);
}
