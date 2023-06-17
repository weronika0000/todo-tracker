package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.*;
import com.softwaremind.todotracker.entity.TaskStatus;
import org.springframework.data.domain.Sort;

import java.util.List;

public interface TaskService {

    AddTaskResponseDto addTask(AddTaskRequestDto taskDto);

    TaskResponseDto getById(Long taskId);

    List<TaskResponseDto> getAllTasks();

    ModifyTaskResponseDto modifyTask(Long taskId, ModifyTaskRequestDto taskDto);

    void deleteTask(Long taskId);


    List<TaskResponseDto> getTasksByStatusSortedByDeadline(TaskStatus status, Sort.Direction sortDirection);

    List<TaskResponseDto> getTasksByStatusSortedByImportance(TaskStatus status, Sort.Direction sortDirection);
}
