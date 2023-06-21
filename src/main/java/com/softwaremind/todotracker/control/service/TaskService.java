package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.*;
import com.softwaremind.todotracker.entity.TaskImportance;
import com.softwaremind.todotracker.entity.TaskStatus;

import java.util.List;

public interface TaskService {

    AddTaskResponseDto addTask(AddAndModifyTaskRequestDto taskDto);
    TaskResponseDto getById(Long taskId);
    List<TaskResponseDto> getAllTasks();
    TaskResponseDto modifyTask(Long taskId, AddAndModifyTaskRequestDto taskDto);
    void deleteTask(Long taskId);
    List<TaskResponseDto> getFilteredTasks(TaskStatus status, TaskImportance importance, String sortBy, String sortDirection);


}
