package com.softwaremind.todotracker.control.service;

import com.softwaremind.todotracker.boundary.dto.*;

import java.util.List;

public interface TaskService {

    AddTaskResponseDto addTask(AddTaskRequestDto taskDto);

    TaskResponseDto getById(Long taskId);

    List<TaskResponseDto> getAllBooks();

    ModifyTaskResponseDto modifyTask(Long taskId, ModifyTaskRequestDto taskDto);

    void deleteTask(Long taskId);
}
