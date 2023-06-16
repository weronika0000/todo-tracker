package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.AddTaskRequestDto;
import com.softwaremind.todotracker.boundary.dto.AddTaskResponseDto;
import com.softwaremind.todotracker.boundary.dto.TaskResponseDto;

import java.util.List;

public interface TaskService {

    AddTaskResponseDto addTask(AddTaskRequestDto taskDto);

    TaskResponseDto getById(Long taskId);

    List<TaskResponseDto> getAllBooks();

    TaskResponseDto updateTask(Long taskId, AddTaskRequestDto taskDto);

    void deleteTask(Long taskId);
}
