package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.AddTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.boundary.dto.TaskResponseDto;

public interface TaskService {

    AddTaskResponseDto addTask(AddTaskRequestDto taskDto);

    TaskResponseDto findById(Long taskId);

    TaskResponseDto updateTask(Long taskId, AddTaskRequestDto taskDto);

    void deleteTask(Long taskId);
}
